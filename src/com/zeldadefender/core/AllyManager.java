package com.zeldadefender.core;

import com.zeldadefender.model.Ally;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Wave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.zeldadefender.view.core.Clock.delta;

public class AllyManager
{
    private Ally ally;

    public AllyManager(Ally ally, float spawnTime)
    {
        this.ally = ally;
    }

    public void update()
    {
        ally.update();
    }

    public Ally getAlly()
    {
        return ally;
    }
}
