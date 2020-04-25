package com.zeldadefender.model.core;

import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Wave;

import static com.zeldadefender.view.core.Clock.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaveManager
{
    private float timeSinceLastSpawn, spawnTime;
    private List<Enemy> enemyType;
    private List<Wave> waveList;
    private Wave currentWave;

    public WaveManager(List<Enemy> enemyType, float spawnTime)
    {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.timeSinceLastSpawn = 0;
        this.currentWave = null;
        this.waveList = new ArrayList<>();
    }

    public void update()
    {
        Wave waveCompleted = null;
        timeSinceLastSpawn += delta();

        if (timeSinceLastSpawn > spawnTime)
        {
            newWave();
            timeSinceLastSpawn = 0;
        }

        for (Wave wave: waveList)
        {
            if (!wave.isWaveCompleted())
            {
                wave.update();
            } else
            {
                waveCompleted = wave;
            }
        }

        if (null != waveCompleted)
        {
            Iterator i = waveList.iterator();
            Wave nextWave = null;
            while(i.hasNext())
            {
                nextWave = (Wave) i.next();
                if (nextWave.equals(waveCompleted))
                {
                    i.remove();
                    break;
                }
            }
        }
    }

    private void newWave()
    {
        currentWave = new Wave(enemyType);
        waveList.add(currentWave);
    }

    public List<Wave> getWaveList()
    {
        return waveList;
    }
}
