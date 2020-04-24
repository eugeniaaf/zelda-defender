package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;
import com.zeldadefender.view.core.TileGrid;

import static com.zeldadefender.view.core.Artist.drawQuadTex;
import static com.zeldadefender.view.core.Artist.quickLoad;
import static com.zeldadefender.view.core.Clock.delta;

public class Ally
{
    private Texture texture;
    private Tile startTile;
    private TileGrid tileGrid;
    private int width, height, health, changeSprite = 0, damage;
    private float speed, x, y;
    private boolean first = true, alive = true;

    public Ally(Texture texture, Tile startTile, TileGrid tileGrid, int width, int height, int damage)
    {
        this.texture = texture;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.startTile = startTile;
        this.tileGrid = tileGrid;
        this.damage = damage;
    }

    public void update()
    {
        draw();
        if (first)
        {
            first = false;
        } else
        {
//            changeSprite++;
        }
    }

    private void checkTile()
    {
    }

    private void die()
    {
        alive = false;
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, width, height);
    }

    public Texture getTexture()
    {
        return texture;
    }

    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getChangeSprite()
    {
        return changeSprite;
    }

    public void setChangeSprite(int changeSprite)
    {
        this.changeSprite = changeSprite;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public boolean isFirst()
    {
        return first;
    }

    public void setFirst(boolean first)
    {
        this.first = first;
    }

    public Tile getStartTile()
    {
        return startTile;
    }

    public void setStartTile(Tile startTile)
    {
        this.startTile = startTile;
    }

    public TileGrid getTileGrid()
    {
        return tileGrid;
    }

    public void setTileGrid(TileGrid tileGrid)
    {
        this.tileGrid = tileGrid;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public static Ally copy(Ally ally)
    {
        return new Ally(ally.getTexture(), ally.getStartTile(), ally.tileGrid, ally.width, ally.height, ally.damage);
    }
}
