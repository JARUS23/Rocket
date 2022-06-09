package com.jarus.rocket.blood;

import com.jarus.rocket.entities.Vector2D;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.worlds.World1;

import java.awt.*;

public class Blood {

    private Vector2D position;
    private float alpha;
    private World1 world;

    public Blood(Vector2D position,World1 world)
    {
        this.position = position;
        this.world = world;
        alpha = 1;
    }

    public void render(Graphics g)
    {
        int xx = (int) world.getHandler().getGameCamera().getxOffset();
        int yy = (int) world.getHandler().getGameCamera().getyOffset();

        Graphics2D g2d = (Graphics2D)g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.drawImage(Assets.blood, (int)(position.getX() - xx), (int)(position.getY() - yy), null);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        alpha -= 0.001f;
        if(alpha < 0)
            world.getEntitymanager().getZombieBlood().remove(this);
    }
}
