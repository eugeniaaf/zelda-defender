package com.zeldadefender.model;

import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;

import java.util.ArrayList;

import static com.zeldadefender.view.core.Artist.*;
import static com.zeldadefender.view.core.Clock.*;

public class Tower
{
    private float x, y, timeSinceLastShot, firingSpeed;
    private int width, height, damage;
    private Texture texture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;

    public Tower(Texture texture, Tile startTile, int width, int height, int damage)
    {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.damage = damage;
        this.width = width;
        this.height = height;

        // testing
        this.firingSpeed = 30;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
    }

    private void shoot()
    {
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(quickLoad("projectile"), x + (width / 2), y + (height / 2), 12, 10));
    }

    public void update()
    {
        timeSinceLastShot += delta();
        if (timeSinceLastShot > firingSpeed)
        {
            shoot();
        }

        for (Projectile projectile: projectiles)
        {
            projectile.update();
        }

        draw();
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, width, height);
    }
}
