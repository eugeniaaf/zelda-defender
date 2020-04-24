package com.zeldadefender.view.core;

import com.zeldadefender.core.Constant;

public class TileGrid
{
    public Tile[][] map;

    public TileGrid()
    {
        map = new Tile[50][38];

        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                map[i][j] = new Tile(i * Constant.TILE_WIDTH, j * Constant.TILE_HEIGHT, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, TileType.Sand);
            }
        }
    }

    public TileGrid(int[][] newMap)
    {
        map = new Tile[50][38];
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                switch (newMap[j][i])
                {
                    case 0:
                        map[i][j] = new Tile(i * Constant.TILE_WIDTH, j * Constant.TILE_HEIGHT, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, TileType.Sand);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * Constant.TILE_WIDTH, j * Constant.TILE_HEIGHT, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, TileType.Path);
                        break;
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord, TileType tileType)
    {
        map[xCoord][yCoord] = new Tile(xCoord * Constant.TILE_WIDTH, yCoord * 32, Constant.TILE_WIDTH, 32, tileType);
    }

    public Tile getTile(int xCoord, int yCoord)
    {
        return map[xCoord][yCoord];
    }

    public void draw()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                Tile tile = map[i][j];
                Artist.drawQuadTex(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
            }
        }
    }
}
