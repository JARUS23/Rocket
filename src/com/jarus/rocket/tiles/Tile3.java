package com.jarus.rocket.tiles;

import com.jarus.rocket.gfx.Assets;

public class Tile3 extends Tile{

    public Tile3(int id)
    {
        super(Assets.wall,id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
