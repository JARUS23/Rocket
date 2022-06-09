package com.jarus.rocket.entities.creatures;

import com.jarus.rocket.entities.Entity;
import com.jarus.rocket.entities.Vector2D;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.tiles.Tile;

import java.awt.geom.AffineTransform;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 80,
            DEFAULT_CREATURE_HEIGHT = 80;

    protected int health;
    protected double maxVelocity;
    protected float speed;
    protected float xMove, yMove;
    protected double angle;
    protected AffineTransform at;
    protected Vector2D velocity;

    public Creature(Handler handler, Vector2D position, int width, int height, double maxVelocity, int health)
    {
        super(handler,position,width,height);
        this.maxVelocity = maxVelocity;
        this.health = health;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        angle = 0;
        this.velocity = new Vector2D();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public double getMaxVelocity() {
        return 3.0f;
    }

    public void move()
    {
        if (!checkEntityCollisions(xMove,0f))
            moveX();
        if (!checkEntityCollisions(0f,yMove))
            moveY();
    }

    public void moveX()
    {
        if (xMove>0)
        {
            //Moving Right
            int tx = (int) (position.getX() + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx,(int) (position.getY() + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int) (position.getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            {
                position.setX(position.getX() + xMove);
            }
            else
            {
                position.setX(tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1);
            }
        }
        else if (xMove<0)
        {
            //Moving Left
            int tx = (int) (position.getX() + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx,(int) (position.getY() + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int) (position.getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            {
                position.setX(position.getX() + xMove);
            }
            else
            {
                position.setX(tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x);
            }
        }
    }

    public void moveY()
    {
        if (yMove<0)
        {
            //Up
            int ty = (int) (position.getY() + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (position.getX() + bounds.x) / Tile.TILEWIDTH,ty) &&
                    !collisionWithTile((int) (position.getX() + bounds.x + bounds.width) / Tile.TILEWIDTH,ty))
            {
                position.setY(position.getY() + yMove);
            }
            else
            {
                position.setY(ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y);
            }
        }
        else if (yMove>0)
        {
            //Down
            int ty = (int) (position.getY() + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) (position.getX() + bounds.x) / Tile.TILEWIDTH,ty) &&
                    !collisionWithTile((int) (position.getX() + bounds.x + bounds.width) / Tile.TILEWIDTH,ty))
            {
                position.setY(position.getY() + yMove);
            }
            else
            {
                position.setY(ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1);
            }
        }
    }

    protected boolean collisionWithTile(int x,int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }
}
