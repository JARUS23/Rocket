package com.jarus.rocket.entities;

import com.jarus.rocket.game.Handler;

import java.awt.*;

public abstract class Entity {

    protected Vector2D position;
    protected Handler handler;
    protected int width,height;
    protected Rectangle bounds;

    public Entity(Handler handler,Vector2D position,int width, int height)
    {
        this.handler = handler;
        this.position = position;
        this.width = width;
        this.height = height;


        bounds = new Rectangle(0,0,width,height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public boolean checkEntityCollisions(float xOffset,float yOffset)
    {
        for (Entity e: handler.getWorld().getEntitymanager().getEntities())
        {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int)(position.getX() + bounds.x + xOffset),
                (int)(position.getY() + bounds.y + yOffset),
                bounds.width,bounds.height);
    }

    //GETTERS
    public Vector2D getPosition()
    {
        return position;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
