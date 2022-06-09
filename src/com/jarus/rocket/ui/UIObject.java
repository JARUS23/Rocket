package com.jarus.rocket.ui;

import com.jarus.rocket.gfx.AssetsSound;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;
    private boolean flag = true;

    public UIObject(float x,float y,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x, (int)y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g,String name);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e)
    {
        if (bounds.contains(e.getX(),e.getY()))
        {
            hovering = true;

            if (flag)
            {
                AssetsSound.mbtnHovering.play();
                flag = false;
            }
        }
        else
        {
            hovering = false;
            flag = true;
        }
    }

    public void onMousePressed(MouseEvent e)
    {
        if (hovering)
            onClick();
    }
}
