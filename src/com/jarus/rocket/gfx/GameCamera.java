package com.jarus.rocket.gfx;

import com.jarus.rocket.entities.Entity;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.tiles.Tile;

public class GameCamera {

    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler,float xOffset,float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace()
    {
        if (xOffset<0)
            xOffset = 0;
        else if (xOffset>handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth())
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();

        if (yOffset<0)
            yOffset = 0;
        else if (yOffset>handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight())
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
    }

    public void centerOnEntity(Entity e)
    {
        xOffset = (float)e.getPosition().getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = (float)e.getPosition().getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float xAmt,float yAmt)
    {
        xOffset = xOffset + xAmt;
        yOffset = yOffset + yAmt;
        checkBlankSpace();
    }

    //GETTERS AND SETTERS
    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
}
