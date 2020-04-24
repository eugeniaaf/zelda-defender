package com.zeldadefender;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.ImageParser;
import com.zeldadefender.view.GameWindow;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Boot
{

    public Boot()
    {
        // Initialize Game Window
        GameWindow.getInstance();
    }

    public static void main(String[] args)
    {
        new Boot();
    }
}
