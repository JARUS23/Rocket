package com.jarus.rocket.states;

import com.jarus.rocket.game.Handler;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.input.KeyManager;

import java.awt.*;

public class Guide extends State{

    public Guide(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick() {
        if (KeyManager.start)
        {
            State.setState(handler.getGame().gameState1);
            KeyManager.start = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.guide,0,0,null);

        //Space
        g.setFont(new Font("Tahoma", Font.PLAIN, 30));
        g.setColor(Color.RED);
        g.drawString("PRESS SPACE TO START THE GAME",450,650);
    }
}
