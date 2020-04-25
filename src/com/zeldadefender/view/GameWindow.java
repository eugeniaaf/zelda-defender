package com.zeldadefender.view;

import com.zeldadefender.core.Constant;
import com.zeldadefender.core.ImageParser;
import com.zeldadefender.core.GameManager;
import com.zeldadefender.view.core.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameWindow
{
    private long window;
    private final ImageParser imageParser = ImageParser.loadImage("src/com/zeldadefender/resources/images/icon.png");

    public GameWindow()
    {
        initialize();
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

        GameManager.init();

        // Rendering loop until the user has attempted to close the window
        // or pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window))
        {
            glfwPollEvents(); // Pool for window events.
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear the framebuffer.
//            Clock.update();
            if (GameManager.isGameStarted())
            {
                GameManager.update();
            }

            glfwSwapBuffers(window); // Swap the colors buffers.
        }
    }
}
