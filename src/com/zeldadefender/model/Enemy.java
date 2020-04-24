package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;
import com.zeldadefender.view.core.TileGrid;

import static com.zeldadefender.view.core.Artist.*;
import static com.zeldadefender.view.core.Clock.*;

public class Enemy
{
    private Texture texture;
    private Tile startTile;
    private TileGrid tileGrid;
    private int width, height, health, changeSprite = 0, dirX = 1, dirY = 0;
    private float speed, x, y;
    private boolean first = true, alive = true;

    public Enemy(Texture texture, Tile startTile, TileGrid tileGrid, int width, int height, float speed)
    {
        this.texture = texture;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.startTile = startTile;
        this.tileGrid = tileGrid;
    }

    public void update()
    {
        if (changeSprite < 12)
        {
            if (dirX > 0)
                this.texture = quickLoad("enemy");
            else if (dirY < 0)
                this.texture = quickLoad("enemy_up");
            else if (dirY > 0)
                this.texture = quickLoad("enemy_down");

        } else
        {
            if (dirX > 0)
                this.texture = quickLoad("enemy_2");
            else if (dirY < 0)
                this.texture = quickLoad("enemy_up_2");
            else if (dirY > 0)
                this.texture = quickLoad("enemy_down_2");
            if (changeSprite > 24) changeSprite = 0;
        }

        if (first)
        {
            first = false;
        } else
        {
            if ((int) (x / Constant.TILE_WIDTH) == 49)
                die();

            x += delta() * speed * dirX;
            y += delta() * speed * dirY;
            changeSprite++;
        }

        checkTile();
    }

    private void checkTile()
    {
        int startX = (int) (startTile.getX() / Constant.TILE_WIDTH);
        int startY = (int) (startTile.getY() / Constant.TILE_HEIGHT);
        int currentX = (int) (x / Constant.TILE_WIDTH);
        int currentY = (int) (y / Constant.TILE_HEIGHT);


        if (currentX == startX + 29 && currentY == startY)
        {
            dirX = 0;
            if (startY > 25)
                dirY = -1;
            else
                dirY = 1;
        }

        if (currentX == startX + 29 && (currentY == startY + 11 || currentY == startY - 12))
        {
            dirY = 0;
            dirX = 1;
        }
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

    public static Enemy copy(Enemy enemy)
    {
        return new Enemy(enemy.getTexture(), enemy.getStartTile(), enemy.tileGrid, enemy.width, enemy.height, enemy.getSpeed());
    }
}
