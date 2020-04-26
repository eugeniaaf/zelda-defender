package com.zeldadefender.model.core;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.ContainerManager;
import com.zeldadefender.model.agent.AllyAgent;
import com.zeldadefender.view.core.Artist;
import jade.core.AgentManager;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.List;

public class AllyManager
{
    private List<AgentController> agentControllers;
    private List<AllyAgent> allyAgents;
    public AllyManager(int x, int y, String name)
    {
        this.agentControllers = new ArrayList<>();
        this.allyAgents = new ArrayList<>();

        createAlly(x + 1, y, name + "_1");
        createAlly(x , y + 1, name + "_2");
        createAlly(x + 1, y + 2, name + "_3");
    }

    public void createAlly(int x, int y, String name)
    {
        Object[] allyArgs = {
                x,
                y,
                Constant.ALLY_WIDTH,
                Constant.ALLY_HEIGHT,
                Constant.ALLY_LIFE,
                Constant.ALLY_MIN_DAMAGE,
                Constant.ALLY_MAX_DAMAGE,
                Artist.quickLoad("ally"),
                this
        };

        try
        {
            this.agentControllers.add(ContainerManager.getInstance().instantiateAgent(name, AllyAgent.class.getName(), allyArgs));
        } catch (StaleProxyException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void update()
    {
        for (AllyAgent allyAgent: allyAgents)
        {
            allyAgent.update();
        }
    }

    public void addAllyAgents(AllyAgent allyAgents)
    {
        this.allyAgents.add(allyAgents);
    }
}
