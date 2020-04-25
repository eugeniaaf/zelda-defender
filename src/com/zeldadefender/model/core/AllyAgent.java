package com.zeldadefender.model.core;

import com.zeldadefender.core.*;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;
import com.zeldadefender.view.core.TileGrid;
import jade.core.Agent;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.zeldadefender.view.core.Artist.drawQuadTex;
import static com.zeldadefender.view.core.Artist.quickLoad;

public class AllyAgent extends Agent
{
    private Texture texture;
    private Tile startTile;
    private TileGrid tileGrid;
    private int width, height, life, changeSprite = 0;
    private float speed, x, y;
    private boolean first = true, alive = true, attacking = false;
    private Enemy target;

    @Override
    protected void setup()
    {
        System.out.println("Hello");
    }

//    public Ally(Texture texture, Tile startTile, TileGrid tileGrid, int width, int height)
//    {
//        this.texture = texture;
//        this.x = startTile.getX();
//        this.y = startTile.getY();
//        this.width = width;
//        this.height = height;
//        this.startTile = startTile;
//        this.tileGrid = tileGrid;
//        this.life = Constant.ALLY_LIFE;
//    }

    public void update()
    {
        if (changeSprite < 12)
        {
            if (attacking) this.texture = quickLoad("ally");
        } else
        {
            if (attacking)
            {
                this.texture = quickLoad("ally_attack");
                attack();
            }
            if (changeSprite > 24) changeSprite = 0;
        }

        if (first)
        {
            first = false;
        } else
        {
            if (alive)
            {
                draw();
                if (attacking)
                {
                    changeSprite++;
                } else
                {
                    this.texture = quickLoad("ally");
                }
            }
        }
    }

    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }

    private void die()
    {
//        List<AllyManager> allyManagers = GameManager.getInstance().getAllyManagers();
//
//        Iterator i = allyManagers.iterator();
//        AllyManager nextAllyManager = null;
//        while (i.hasNext())
//        {
//            nextAllyManager = (AllyManager) i.next();
//            if (nextAllyManager.getAlly().equals(this))
//            {
//                alive = false;
//                if (null != this.target)
//                {
//                    this.target.setAttacking(false);
//                    this.target.setTarget(null);
//                }
//                i.remove();
//                break;
//            }
//        }
    }

    public void draw()
    {
        drawQuadTex(texture, x, y, width, height);
    }

    public void attack()
    {
        if (null == this.target || !this.target.isAlive())
        {
            this.attacking = false;
            return;
        }

        Random randomGenerator = new Random();
        int damage = randomGenerator.nextInt(Constant.ALLY_MAX_DAMAGE) + Constant.ALLY_MIN_DAMAGE;
        this.target.setDamage(damage);
    }

    public void setDamage(int damage)
    {
//        this.life -= damage;
//        if (this.life <= 0) this.die();
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

    public int getLife()
    {
        return life;
    }

    public void setLife(int life)
    {
        this.life = life;
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

    public Enemy getTarget()
    {
        return target;
    }

    public void setTarget(Enemy target)
    {
        this.target = target;
    }
}
