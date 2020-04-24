package com.zeldadefender.core;

import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Wave;

import static com.zeldadefender.view.core.Clock.*;

import java.util.ArrayList;
import java.util.List;

public class WaveManager
{
    private float timeSinceLastSpawn, spawnTime;
    private int waveNumber;
    private List<Enemy> enemyType;
    private List<Wave> waveList;
    private Wave currentWave;

    public WaveManager(List<Enemy> enemyType, float spawnTime)
    {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.timeSinceLastSpawn = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        this.waveList = new ArrayList<>();
    }

    public void update()
    {
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
                System.out.println("Wave is over!");
                waveList.remove(wave);
            }
        }
    }

    private void newWave()
    {
        currentWave = new Wave(enemyType);
        waveList.add(currentWave);
        waveNumber++;
    }
}
