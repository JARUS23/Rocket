package com.jarus.rocket.states;

import com.jarus.rocket.game.Handler;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.worlds.World1;

import java.awt.*;

public class GameOver extends State{

    private int xSize;
    private String text;

    public GameOver(Handler handler)
    {
        super(handler);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        xSize = (int) toolkit.getScreenSize().getWidth();

        text = "Note: To Play the game again, please close the game and re-run it.";
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background,0,0,null);

        //GAME OVER
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.PLAIN, 70));
        g.drawString("GAME OVER", xSize / 2 - 200, 100);

        //SCORE
        g.setFont(new Font("Tahoma", Font.PLAIN, 50));
        g.drawString("Score - "+ World1.score, xSize / 2 - 100, 200);

        // INFO
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.drawString(text, xSize / 2 - 300, 300);
    }
}
