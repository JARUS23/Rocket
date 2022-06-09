package com.jarus.rocket.entities;

import com.jarus.rocket.entities.creatures.Zombie;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.worlds.World1;

import java.awt.*;

public class MachineBullet extends Entity{

    private final static int BULLETDIAMETER = 7;
    private final int bulletVelocity = 20;
    private Vector2D velocity;
    private World1 world;

    public MachineBullet(Handler handler, Vector2D position, Vector2D velocity, World1 world)
    {
        super(handler, position, BULLETDIAMETER,BULLETDIAMETER);
        this.velocity = velocity;
        this.world = world;
    }

    public void tick()
    {
        position.setX((float) (position.getX() + (velocity.getX()*bulletVelocity)));
        position.setY((float) (position.getY() + (velocity.getY()*bulletVelocity)));


        Point center = new Point((int)position.getX() - BULLETDIAMETER/2, (int)position.getY() - BULLETDIAMETER/2);


        for(int i = 0; i < world.getEntitymanager().getZombieAndBullets().size(); i++)
        {

            if(world.getEntitymanager().getZombieAndBullets().get(i) instanceof MachineBullet)
                continue;

            Zombie zombie = (Zombie)world.getEntitymanager().getZombieAndBullets().get(i);

            if(zombie.getBounds().contains(center))
            {
                zombie.machinehit();
                world.getEntitymanager().getZombieAndBullets().remove(this);
            }
        }

        if (position.getX() < 0 || position.getY() < 0 || position.getX()>1366 || position.getY()>768)
        {
            EntityManager.removeZombieEntity(this);
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.YELLOW);

        g.fillOval((int)(position.getX() - BULLETDIAMETER/2),
                (int)(position.getY() - BULLETDIAMETER/2),
                BULLETDIAMETER, BULLETDIAMETER);
    }
}
