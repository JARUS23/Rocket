package com.jarus.rocket.tiles;

import com.jarus.rocket.gfx.Assets;

public class Tile1 extends Tile{

    public Tile1(int id)
    {
        super(Assets.water,id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
