package com.zeldadefender.view.core;

import org.lwjgl.glfw.GLFW;

public class Clock
{
    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float d = 0, multipler = 1;

    public static long getTime()
    {
        return GLFW.glfwGetTimerValue() / 1000;
    }

    public static float getDelta()
    {
        long currentTime = getTime();
        int delta =  (int) (currentTime - lastFrame);
        lastFrame = getTime();

        return delta * .001f;
    }

    public static float delta()
    {
        if (paused)
            return 0;
        else
            return d * multipler;
    }

    public static float totalTime()
    {
        return totalTime;
    }

    public static float multipler()
    {
        return multipler;
    }

    public static void update()
    {
        d = getDelta();
        totalTime += d;
    }

    public static void changeMultiplier(int change)
    {
        if (multipler + change < -1 && multipler + change > 7)
        {

        } else
        {
            multipler += change;
        }
    }

    public static void pause()
    {
        if (paused)
            paused = false;
        else
            paused = true;
    }
}
