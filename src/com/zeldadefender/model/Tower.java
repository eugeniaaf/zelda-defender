package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.WaveManager;
import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zeldadefender.view.core.Artist.*;
import static com.zeldadefender.view.core.Clock.*;

public class Tower
{
    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height;
    private Texture texture;
    private Tile startTile;
    private List<Projectile> projectiles;
    private List<WaveManager> waveManagers;
    private Enemy target;

    public Tower(Texture texture, Tile startTile, int width, int height, List<WaveManager> waveManagers)
    {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.firingSpeed = 30;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
        this.waveManagers = waveManagers;
    }

    private void shoot()
    {
        timeSinceLastShot = 0;
        Random randomGenerator = new Random();
        int damage = randomGenerator.nextInt(Constant.TOWER_MAX_DAMAGE) + Constant.TOWER_MIN_DAMAGE;
        projectiles.add(new Projectile(quickLoad("projectile"), this.target, x, y, 8, 10, 50, damage));
    }

    public void update()
    {
        this.target = findTarget();
        if (null != this.target)
        {
            timeSinceLastShot += delta();
            if (timeSinceLastShot > firingSpeed)
                shoot();
        }

        for (Projectile projectile : projectiles)
            projectile.update();

        draw();
    }

    private Enemy findTarget()
    {
        Enemy t = null;
        for (WaveManager waveManager: waveManagers)
        {
            for (Wave wave: waveManager.getWaveList())
            {
                for (Enemy enemy: wave.getEnemyList())
                {
                    int xEnemy = (int) enemy.getX() / Constant.TILE_WIDTH;
                    int yEnemy = (int) enemy.getY() / Constant.TILE_HEIGHT;
                    int xTower = (int) this.x / Constant.TILE_WIDTH;
                    int yTower = (int) this.y / Constant.TILE_HEIGHT;

                    if ((xEnemy > (xTower - 10) && xEnemy < (xTower + 10)) &&
                            (yEnemy > (yTower - 5) && yEnemy < (yTower + 5)))
                    {
                        t = enemy;
                    }
                }
            }
        }

        return t;
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, width, height);
    }
}
