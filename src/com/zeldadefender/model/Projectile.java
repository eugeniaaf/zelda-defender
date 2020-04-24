package com.zeldadefender.model;

import com.zeldadefender.view.core.Texture;

import static com.zeldadefender.view.core.Clock.*;
import static com.zeldadefender.view.core.Artist.*;

public class Projectile
{
    Texture texture;
    float x, y, speed;
    int damage;

    public Projectile(Texture texture, float x, float y, float speed, int damage)
    {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public void update()
    {
        x += delta() * speed;
        draw();
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, 8, 10);
    }
}
