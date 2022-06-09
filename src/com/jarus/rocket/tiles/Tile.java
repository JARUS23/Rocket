package com.jarus.rocket.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC
    public static Tile tiles[] = new Tile[256];
    public static Tile tile1 = new Tile1(0);   // water
    public static Tile tile2 = new Tile2(1);   // wall7
    public static Tile tile3 = new Tile3(2);   // wall
    public static Tile bush1 = new Bushes1(3); // bushes1
    public static Tile bush2 = new Bushes2(4); // bushes2
    public static Tile bush3 = new Bushes3(5); // bushes3
    public static Tile bo = new Box(6); //box

    //CLASS
    public static final int TILEWIDTH = 80, TILEHEIGHT = 80;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture,int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
    }

    public boolean isSolid()
    {
        return false;
    }

    //GETTER
    public int getId()
    {
        return id;
    }
}
