package com.zeldadefender.core;

import com.zeldadefender.view.GameWindow;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class ContainerManager
{
    private static ContainerManager instance = null;
    private ContainerController containerController;

    private ContainerManager()
    {
        init();
    }

    public static ContainerManager getInstance()
    {
        if (null == instance)
        {
            instance = new ContainerManager();
        }

        return instance;
    }

    private void init()
    {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, "Tower Defense");
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");

        containerController = runtime.createMainContainer(profile);


        // Initialize Game Window
        GameWindow.getInstance();
    }

    public AgentController instantiateAgent(String name, String className, Object[] args) throws StaleProxyException
    {
        AgentController agentController = containerController.createNewAgent(name, className, args);
        agentController.start();
        return agentController;
    }

    public AgentController instantiateAgent(String name, String className) throws StaleProxyException
    {
        return instantiateAgent(name, className, new Object[0]);
    }
}