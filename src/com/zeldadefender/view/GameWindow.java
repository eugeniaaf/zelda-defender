package com.zeldadefender.view;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.ImageParser;
import com.zeldadefender.model.Enemy;
import com.zeldadefender.model.Tower;
import com.zeldadefender.model.Wave;
import com.zeldadefender.view.core.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.zeldadefender.view.core.Artist.*;

public class GameWindow
{
    private static GameWindow instance = null;
    private long window;
    private final ImageParser imageParser = ImageParser.loadImage("src/com/zeldadefender/resources/images/icon.png");

    public GameWindow()
    {
        initialize();
    }

    public static GameWindow getInstance()
    {
        if (null == instance)
            instance = new GameWindow();

        return instance;
    }

    public void initialize()
    {
        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Initialize window configuration.
     */
    private void init()
    {
        // Setup error callback. >> will print the error message in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW.
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW.
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // not visible
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // not resizable

        // Create the window.
        window = glfwCreateWindow(Constant.GAME_WIDTH, Constant.GAME_HEIGHT, "Zelda Defender", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. >> called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        // Get the thread stack and push a new frame.
        try (MemoryStack stack = stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            /// Get the window size passed to glfwCreateWindow.
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor.
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window.
            glfwSetWindowPos(window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        // Make the OpenGL context current.
        glfwMakeContextCurrent(window);

        // Make the window visible.
        glfwShowWindow(window);

        GLFWImage image = GLFWImage.malloc();
        GLFWImage.Buffer imageBuffer = GLFWImage.malloc(1);
        image.set(imageParser.getWidth(), imageParser.getHeight(), imageParser.getImage());
        imageBuffer.put(0, image);
        glfwSetWindowIcon(window, imageBuffer);
    }

    /**
     * Rendering loop.
     */
    private void loop()
    {
        GL.createCapabilities();


        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        TileGrid tileGrid = new TileGrid(Constant.GAME_BOARD);
        List<Enemy> topEnemies = new ArrayList<>();

        Tower tower = new Tower(quickLoad("tower"), tileGrid.getTile(0, 0), 48, 32, 10);
        for (int i = 0; i < 3; i++)
        {
            int y = 8 - i;

            Enemy enemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, y), tileGrid, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, 3);
            topEnemies.add(enemy);
        }
        List<Enemy> downEnemies = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            int y = 28 + i;

            Enemy enemy = new Enemy(quickLoad("enemy"), tileGrid.getTile(i, y), tileGrid, Constant.TILE_WIDTH, Constant.TILE_HEIGHT, 3);
            downEnemies.add(enemy);
        }

        Wave topWave = new Wave(150, topEnemies);
        Wave downWave = new Wave(150, downEnemies);

        // Rendering loop until the user has attempted to close the window
        // or pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window))
        {
            glfwPollEvents(); // Pool for window events.
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear the framebuffer.

            Clock.update();

            tileGrid.draw();
            topWave.update();
            downWave.update();
            tower.draw();

            glfwSwapBuffers(window); // Swap the colors buffers.
        }
    }
}