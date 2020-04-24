package com.zeldadefender.core;

import com.zeldadefender.model.Ally;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Wave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.zeldadefender.view.core.Clock.delta;

public class AllyManager
{
    private float timeSinceDeath, spawnTime;
    private Ally ally;

    public AllyManager(Ally ally, float spawnTime)
    {
        this.ally = ally;
        this.spawnTime = spawnTime;
        this.timeSinceDeath = 0;
    }

    public void update()
    {
        timeSinceDeath += delta();

        if (timeSinceDeath > spawnTime)
        {
            respawn();
            timeSinceDeath = 0;
        }

        ally.update();


//        if (null != waveCompleted)
//        {
//            Iterator i = waveList.iterator();
//            Wave nextWave = null;
//            while(i.hasNext())
//            {
//                nextWave = (Wave) i.next();
//                if (nextWave.equals(waveCompleted))
//                {
//                    i.remove();
//                    break;
//                }
//            }
//        }
    }

    private void respawn()
    {
    }
}
