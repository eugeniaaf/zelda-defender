package com.zeldadefender.model;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.WaveManager;
import com.zeldadefender.view.core.TileGrid;

import java.util.ArrayList;
import java.util.List;

import static com.zeldadefender.view.core.Artist.quickLoad;

public class Game
{
    private TileGrid tileGrid;
    private List<WaveManager> waveManagers;
    private List<Tower> towers;

    public Game(int[][] map)
    {
        this.tileGrid = new TileGrid(map);
        this.waveManagers = new ArrayList<>();

        this.towers = new ArrayList<>();

        List<Enemy> topEnemies = new ArrayList<>();
        List<Enemy> botEnemies = new ArrayList<>();

        for (int i = 0; i < 3; i++)
        {
            int topY = 8 - i;
            int botY = 28 + i;

            Enemy topEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, topY), tileGrid, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, 3);
            topEnemies.add(topEnemy);
            Enemy botEnemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, botY), tileGrid, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, 3);
            botEnemies.add(botEnemy);

        }
        this.waveManagers.add(new WaveManager(topEnemies, 100));
        this.waveManagers.add(new WaveManager(botEnemies, 100));

        for (int i = 0; i < 3; i++)
        {
            switch (i)
            {
                case 0:
                    towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 3), 48, 32, 10, waveManagers));
                    break;
                case 1:
                    towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(20, 25), 48, 32, 10, waveManagers));
                    break;
                case 2:
                    towers.add(new Tower(quickLoad("tower"), tileGrid.getTile(40, 14), 48, 32, 10, waveManagers));
                    break;
            }

        }


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
    }
}
