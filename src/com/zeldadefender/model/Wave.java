package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.view.core.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.zeldadefender.view.core.Clock.*;

public class Wave
{
    private float timeSinceLastSpawn, spawnTime;
    private List<Enemy> enemyType;
    private List<Enemy> enemyList;

    public Wave(float spawnTime, List<Enemy> enemyType)
    {
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;

        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<>();
    }

    public void update()
    {
        timeSinceLastSpawn += delta();

        if (timeSinceLastSpawn > spawnTime)
        {
            spawn();
            timeSinceLastSpawn = 0;
        }

        for (Enemy enemy: enemyList)
        {
            if (enemy.isAlive())
            {
                enemy.update();
                enemy.draw();
            }
        }
    }

    private void spawn()
    {
        for (Enemy enemy: enemyType)
        {
            enemyList.add(new Enemy(enemy.getTexture(), enemy.getStartTile(), enemy.getTileGrid(), Constant.TILE_WIDTH, Constant.TILE_HEIGHT, enemy.getSpeed()));
        }
    }
}
