package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.view.core.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.zeldadefender.view.core.Clock.*;

public class Wave
{
    private List<Enemy> enemyType;
    private List<Enemy> enemyList;
    private boolean waveCompleted;

    public Wave(List<Enemy> enemyType)
    {
        this.enemyType = enemyType;
        this.waveCompleted = false;
        enemyList = new ArrayList<>();
        spawn();
    }

    public void update()
    {
        boolean allEnemiesDead = true;

        for (Enemy enemy: enemyList)
        {
            if (enemy.isAlive())
            {
                allEnemiesDead = false;
                enemy.update();
                enemy.draw();
            }
        }

        if (allEnemiesDead)
            waveCompleted = true;
    }

    private void spawn()
    {
        for (Enemy enemy: enemyType)
        {
            enemyList.add(new Enemy(enemy.getTexture(), enemy.getStartTile(), enemy.getTileGrid(), Constant.TILE_WIDTH, Constant.TILE_HEIGHT, enemy.getSpeed()));
        }
    }

    public boolean isWaveCompleted()
    {
        return waveCompleted;
    }

    public List<Enemy> getEnemyList()
    {
        return enemyList;
    }
}
