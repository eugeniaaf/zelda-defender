package com.zeldadefender.core;

import com.zeldadefender.model.core.AllyManager;
import com.zeldadefender.view.GameWindow;
import com.zeldadefender.view.core.TileGrid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    public static GameManager instance = null;
    private static boolean gameStarted;
    private static TileGrid tileGrid;

    private ContainerManager containerManager;
//    private List<WaveManager> waveManagers;
//    private List<Tower> towers;
    private static List<AllyManager> allyManagers;

    private GameManager()
    {
        gameStarted = false;

        // prevent gui crash
        EventQueue.invokeLater(() ->
        {
            ContainerManager.getInstance();
            gameStarted = true;
        });
        new GameWindow();
    }

    public static GameManager getInstance()
    {
        if (null == instance)
            instance = new GameManager();

        return instance;
    }

    public static void init()
    {
        tileGrid = new TileGrid(Constant.GAME_BOARD);
//        this.waveManagers = new ArrayList<>();
//        this.towers = new ArrayList<>();
        allyManagers = new ArrayList<>();

        allyManagers.add(new AllyManager(19, 6, "TopAlly"));

//        List<Enemy> topEnemies = new ArrayList<>();
//        List<Enemy> botEnemies = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++)
//        {
//            int topY = 8 - i;
//            int botY = 28 + i;
//
//            Enemy topEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, topY), tileGrid, 32, Constant.TILE_HEIGHT, 3);
//            topEnemies.add(topEnemy);
//            Enemy botEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, botY), tileGrid, 32, Constant.TILE_HEIGHT, 3);
//            botEnemies.add(botEnemy);
//
//        }
//        this.waveManagers.add(new WaveManager(topEnemies, 150));
//        this.waveManagers.add(new WaveManager(botEnemies, 150));
//
//        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 3), 48, 32, waveManagers));
//        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 25), 48, 32, waveManagers));
//        this.towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(40, 14), 48, 32, waveManagers));
//
//        for (int i = 0; i < 3; i++)
//        {
//            if (i == 0)
//                this.allyManagers.add(createAllyManager(19 + 2, 6 + i));
//            else
//                this.allyManagers.add(createAllyManager(19 + i, 6 + i));
//        }
//
//        for (int i = 0; i < 3; i++)
//        {
//            if (i == 0)
//                this.allyManagers.add(createAllyManager(19 + 2, 28 + i));
//            else
//                this.allyManagers.add(createAllyManager(19 + i, 28 + i));
//        }
//
//        for (int i = 0; i < 3; i++)
//        {
//            if (i == 0)
//                this.allyManagers.add(createAllyManager(39 + 2, 17 + i));
//            else
//                this.allyManagers.add(createAllyManager(39 + i, 17 + i));
//        }
    }

    public static void update()
    {
        tileGrid.draw();

//        for (WaveManager waveManager: waveManagers)
//        {
//            waveManager.update();
//        }
//
//        for (Tower tower: towers)
//        {
//            tower.update();
//        }
//
        for (AllyManager allyManager: allyManagers)
        {
            allyManager.update();
        }
    }

//    public List<AllyManager> getAllyManagers()
//    {
//        return allyManagers;
//    }

//    public List<WaveManager> getWaveManagers()
//    {
//        return waveManagers;
//    }

    public static boolean isGameStarted()
    {
        return gameStarted;
    }
}
