package com.zeldadefender.model;

import com.zeldadefender.view.core.Texture;

import static com.zeldadefender.view.core.Clock.*;
import static com.zeldadefender.view.core.Artist.*;

public class Projectile
{
    private Texture texture;
    private Enemy target;
    private float x, y, width, height, speed, xVelocity, yVelocity;
    private int damage;
    private boolean alive;

    public Projectile(Texture texture, Enemy target, float x, float y,float width, float height, float speed, int damage)
    {
        this.texture = texture;
        this.target = target;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.damage = damage;
        this.xVelocity = 0;
        this.yVelocity = 0;

        calculateDiretion();
    }

    private void calculateDiretion()
    {
        if (null != target)
        {
            this.alive = true;
            float totalAllowedMovement = 1.0f;
            float xDistanceFromTarget = Math.abs(target.getX() - x + 8);
            float yDistanceFromTarget = Math.abs(target.getY() - y + 10);
            float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
            float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
            xVelocity = xPercentOfMovement;
            yVelocity = totalAllowedMovement - xPercentOfMovement;

            if (target.getX() < x)
                xVelocity *= -1;
            if (target.getY() < y)
                yVelocity *= -1;
        }
    }

    public void update()
    {
        if (alive)
        {
            x += xVelocity * speed * delta();
            y += yVelocity * speed * delta();
            if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight()))
            {
                this.target.setDamage(damage);
                alive = false;
            }

            draw();
        }
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, 8, 10);
    }
}
