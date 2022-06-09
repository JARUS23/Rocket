package com.jarus.rocket.states;

import com.jarus.rocket.game.Handler;
import com.jarus.rocket.worlds.World1;

import java.awt.*;

public class GameState1 extends State{

    private World1 world1;

    public GameState1(Handler handler)
    {
        super(handler);
        world1 = new World1(handler,"res/world/world1.txt");
        handler.setWorld(world1);
    }

    @Override
    public void tick() {
        world1.tick();
    }

    @Override
    public void render(Graphics g) {
        world1.render(g);
    }
}
