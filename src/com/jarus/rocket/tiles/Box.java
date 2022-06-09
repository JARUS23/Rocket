package com.jarus.rocket.tiles;

import com.jarus.rocket.gfx.Assets;

public class Box extends Tile{

    public Box(int id)
    {
        super(Assets.box,id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
