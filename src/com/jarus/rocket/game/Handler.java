package com.jarus.rocket.game;

import com.jarus.rocket.gfx.GameCamera;
import com.jarus.rocket.input.KeyManager;
import com.jarus.rocket.input.MouseManager;
import com.jarus.rocket.worlds.World1;

public class Handler {

    private Game game;
    private World1 world1;

    public Handler(Game game)
    {
        this.game = game;
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    // GETTERS and SETTERS
    public Game getGame()
    {
        return game;
    }

    public World1 getWorld()
    {
        return world1;
    }

    public void setWorld(World1 world1)
    {
        this.world1 = world1;
    }
}
