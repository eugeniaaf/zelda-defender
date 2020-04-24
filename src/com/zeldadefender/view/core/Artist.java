package com.zeldadefender.view.core;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class Artist
{
    public static boolean checkCollision(float x1, float y1, float width1, float height1,
                                         float x2, float y2, float width2, float height2)
    {
        return (x1 + width1 > x2 &&  x1 < x2 + width2 && y1 + height1 > y2 && y1 < y2 + height2);
    }

    public static void drawQuad(float x, float y, float width, float height)
    {
        glBegin(GL_QUADS);
        glVertex2f(x, y); // Top left corner.
        glVertex2f(x + width,y); // Top right corner.
        glVertex2f(x + width, y + height); // Bottom right corner.
        glVertex2f(x, y + height); // Bottom left corner.
        glEnd();
    }

    public static void drawQuadTex(Texture texture, float x, float y, float width, float height)
    {
        texture.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        glTexCoord2f(1, 1);
        glVertex2f(width, height);
        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static Texture quickLoad(String name)
    {
        Texture texture = null;
        texture = new com.zeldadefender.view.core.Texture("src/com/zeldadefender/resources/images/" + name + ".png");

        return texture;
    }
}
