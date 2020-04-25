package com.zeldadefender.core;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.WaveManager;
import com.zeldadefender.model.Ally;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Tower;
import com.zeldadefender.view.GameWindow;
import com.zeldadefender.view.core.TileGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.zeldadefender.view.core.Artist.quickLoad;

public class Game
{
    private TileGrid tileGrid;
    private List<WaveManager> waveManagers;
    private List<Tower> towers;
    private List<AllyManager> allyManagers;
    private static Game instance = null;

    private Game(int[][] map)
    {
        this.tileGrid = new TileGrid(map);
        this.waveManagers = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.allyManagers = new ArrayList<>();

        List<Enemy> topEnemies = new ArrayList<>();
        List<Enemy> botEnemies = new ArrayList<>();

        for (int i = 0; i < 3; i++)
        {
            int topY = 8 - i;
            int botY = 28 + i;

            Enemy topEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, topY), tileGrid, 32, Constant.TILE_HEIGHT, 3);
            topEnemies.add(topEnemy);
            Enemy botEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, botY), tileGrid, 32, Constant.TILE_HEIGHT, 3);
            botEnemies.add(botEnemy);

        }
        this.waveManagers.add(new WaveManager(topEnemies, 150));
        this.waveManagers.add(new WaveManager(botEnemies, 150));

        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 3), 48, 32, waveManagers));
        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 25), 48, 32, waveManagers));
        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(40, 14), 48, 32, waveManagers));

        for (int i = 0; i < 3; i++)
        {
            if (i == 0)
                this.allyManagers.add(createAllyManager(19 + 2, 6 + i));
            else
                this.allyManagers.add(createAllyManager(19 + i, 6 + i));
        }

        for (int i = 0; i < 3; i++)
        {
            if (i == 0)
                this.allyManagers.add(createAllyManager(19 + 2, 28 + i));
            else
                this.allyManagers.add(createAllyManager(19 + i, 28 + i));
        }

        for (int i = 0; i < 3; i++)
        {
            if (i == 0)
                this.allyManagers.add(createAllyManager(39 + 2, 17 + i));
            else
                this.allyManagers.add(createAllyManager(39 + i, 17 + i));
        }
    }

    public static Game getInstance()
    {
        if (null == instance)
            instance = new Game(Constant.GAME_BOARD);

        return instance;
    }

    public void update()
    {
        tileGrid.draw();



        for (WaveManager waveManager: waveManagers)
        {
            waveManager.update();
        }

        for (Tower tower: towers)
        {
            tower.update();
        }

        for (AllyManager allyManager: allyManagers)
        {
            allyManager.update();
        }
    }

    public void respawnAlly(int x, int y)
    {
        try {
            Thread.sleep(5000);
            System.out.println("foi");
            this.allyManagers.add(createAllyManager(x, y));
        } catch (Exception e) {}
    }

    private AllyManager createAllyManager(int x, int y)
    {
        return new AllyManager(new Ally(quickLoad("ally"), tileGrid.getTile(x, y), tileGrid, 32, 16), 100);
    }

    public List<AllyManager> getAllyManagers()
    {
        return allyManagers;
    }

    public List<WaveManager> getWaveManagers()
    {
        return waveManagers;
    }
}
