package com.zeldadefender.model.agent;

import com.zeldadefender.core.*;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.core.AllyManager;
import com.zeldadefender.view.core.Texture;
import com.zeldadefender.view.core.Tile;
import com.zeldadefender.view.core.TileGrid;
import jade.core.Agent;

import java.util.List;
import java.util.Random;

import static com.zeldadefender.view.core.Artist.drawQuadTex;
import static com.zeldadefender.view.core.Artist.quickLoad;

public class AllyAgent extends Agent
{
    private Texture texture;
    private TileGrid tileGrid;
    private int width, height, life, minDamage, maxDamage, changeSprite = 0;
    private float speed, x, y;
    private boolean first = true, alive = true, attacking = false;
    private Enemy target;
    private AllyManager allyManager;

    @Override
    protected void setup()
    {
        Object[] args = getArguments();
        if (null != args && args.length > 0)
        {
            this.x = (int) args[0] * Constant.TILE_WIDTH;
            this.y = (int) args[1] * Constant.TILE_HEIGHT;
            this.width = (int) args[2];
            this.height = (int) args[3];
            this.life = (int) args[4];
            this.minDamage = (int) args[5];
            this.maxDamage = (int) args[6];
            this.texture = (Texture) args[7];
            this.allyManager = (AllyManager) args[8];

            this.allyManager.addAllyAgents(this);
        } else
        {
            System.out.println("Missing args!");
            doDelete();
        }
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

}
