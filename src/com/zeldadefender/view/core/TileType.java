package com.zeldadefender.view.core;

public enum TileType
{
    Sand("sand", true),
    Path("path", false);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable)
    {
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
