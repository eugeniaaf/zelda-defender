package com.zeldadefender.core;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.MissingResourceException;

public class ImageParser
{
    private ByteBuffer image;
    private int width, height;

    public ImageParser(ByteBuffer image, int width, int height)
    {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public static ImageParser loadImage(String path)
    {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            image = STBImage.stbi_load(path, w, h, comp, 4);
            if (image == null) System.out.println("Could not load image resources.");

            width = w.get();
            height = h.get();
        }

        return new ImageParser(image, width, height);
    }

    public ByteBuffer getImage()
    {
        return image;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
