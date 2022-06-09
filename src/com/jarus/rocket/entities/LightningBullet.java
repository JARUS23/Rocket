package com.jarus.rocket.entities;

import com.jarus.rocket.entities.creatures.Zombie;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.worlds.World1;

import java.awt.*;

public class LightningBullet extends Entity{

    private final static int BULLETDIAMETER = 8;
    private final int bulletVelocity = 15;
    private Vector2D velocity;
    private World1 world;
    float startX,startY;

    public LightningBullet(Handler handler, Vector2D position, Vector2D velocity, World1 world)
    {
        super(handler, position, BULLETDIAMETER,BULLETDIAMETER);
        this.velocity = velocity;
        this.world = world;
        startX = (float)position.getX();
        startY = (float)position.getY();
    }

    @Override
    public void tick()
    {
        position.setX((float) (position.getX() + (velocity.getX()*bulletVelocity)));
        position.setY((float) (position.getY() + (velocity.getY()*bulletVelocity)));

        Point center = new Point((int)position.getX() - BULLETDIAMETER/2, (int)position.getY() - BULLETDIAMETER/2);


        for(int i = 0; i < world.getEntitymanager().getZombieAndBullets().size(); i++)
        {

            if(world.getEntitymanager().getZombieAndBullets().get(i) instanceof LightningBullet)
                continue;

            Zombie zombie = (Zombie)world.getEntitymanager().getZombieAndBullets().get(i);

            if(zombie.getBounds().contains(center))
            {
                zombie.lighthit();
                world.getEntitymanager().getZombieAndBullets().remove(this);
            }
        }


        if (position.getX() < 0 || position.getY() < 0 || position.getX()>1366 || position.getY()>768)
        {
            EntityManager.removeZombieEntity(this);
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);

        g.fillOval((int)(position.getX() - BULLETDIAMETER/2),
                (int)(position.getY() - BULLETDIAMETER/2),
                BULLETDIAMETER, BULLETDIAMETER);

        while((int)startX != (int)position.getX())
        {
            g.fillOval((int)(startX - BULLETDIAMETER/2),
                    (int)(startY - BULLETDIAMETER/2),
                    BULLETDIAMETER, BULLETDIAMETER);
            startX = (float) (startX + (velocity.getX()*1));
            startY = (float) (startY + (velocity.getY()*1));
            //System.out.println(startX+" "+startY);
        }
    }
}
