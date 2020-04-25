package com.zeldadefender.model.core;

import com.zeldadefender.core.ContainerManager;
import jade.wrapper.StaleProxyException;

public class AllyManager
{
    private AllyAgent allyAgent;
    public AllyManager(int x, int y)
    {
        Object[] args = {x, y};

        try
        {
//            Object[] towerArgs = {
//                    20,     // Min Attack Damage
//                    80,     // Max Attack Damage
//                    tower
//            };
            Object[] allyArgs = {
                    250,    // Life
                    10,     // Min Attack Damage
                    30,     // Max Attack Damage
            };

            ContainerManager.getInstance().instantiateAgent("Ally", AllyAgent.class.getName(), allyArgs);
        } catch (StaleProxyException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
//        try
//        {
//            ContainerManager.getInstance().instantiateAgent("Ally", Ally.class.getName());
//        } catch (StaleProxyException staleProxyException)
//        {
//            Logger.getLogger(Boot.class.getName()).log(Level.SEVERE, null, staleProxyException);
//        }
    }

//    public AllyManager(Ally ally, float spawnTime)
//    {
//        this.ally = ally;
//    }

    public void update()
    {
//        this.allyAgent.update();
    }

    public AllyAgent getAllyAgent()
    {
        return allyAgent;
    }
}
