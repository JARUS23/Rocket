package com.jarus.rocket.entities.creatures;

import com.jarus.rocket.entities.Entity;
import com.jarus.rocket.entities.Vector2D;
import com.jarus.rocket.game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Steering {

    private Zombie zombie;
    private Player player;
    private Handler handler;


    private Vector2D ahead, ahead2, zombiePos, avoidance;
    private final int MAXSEEAHEAD = 100;

    private int width, height;

    public Steering(Zombie zombie,Player player,Handler handler)
    {
        this.zombie = zombie;
        this.player = player;
        this.handler = handler;
        width = zombie.getWidth()/2;
        height  = zombie.getHeight()/2;

        ahead = new Vector2D();
        ahead2 = new Vector2D();
        avoidance = new Vector2D();
    }

    public Vector2D seek(Vector2D target)
    {
        Vector2D desiredVelocity = target.substract(zombie.getPosition());
        desiredVelocity = desiredVelocity.normalize();
        desiredVelocity.multyplyByScalar(zombie.getMaxVelocity());  //double
        return desiredVelocity.substract(zombie.getVelocity());     //Vector2D
    }

    public Vector2D arrive(Vector2D target)
    {
        Vector2D desiredVelocity = target.substract(zombie.getPosition());
        double distance = desiredVelocity.getMagnitude();
        desiredVelocity = desiredVelocity.normalize();
        if(distance < Zombie.ZOMBIESIZE){
            desiredVelocity = desiredVelocity.multyplyByScalar(zombie.getMaxVelocity()*distance/Zombie.ZOMBIESIZE);

        }else{
            desiredVelocity = desiredVelocity.multyplyByScalar(zombie.getMaxVelocity());
        }
        return desiredVelocity.substract(zombie.getVelocity());
    }

    public Vector2D flee(Vector2D target)
    {
        Vector2D desiredVelocity = zombie.getPosition().substract(target);
        desiredVelocity = desiredVelocity.normalize();
        desiredVelocity.multyplyByScalar(zombie.getMaxVelocity());
        return desiredVelocity.substract(zombie.getVelocity());
    }

    public Vector2D obstacleAvoidance()
    {
        Vector2D position = zombie.getPosition();
        Vector2D velocity = zombie.getVelocity();

        velocity = velocity.normalize();

        ahead = new Vector2D(position.getX() + width + velocity.getX()*MAXSEEAHEAD,
                position.getY() + height + velocity.getY()*MAXSEEAHEAD);

        ahead2 = new Vector2D(position.getX() + width + velocity.getX()*MAXSEEAHEAD*0.5,
                position.getY() + height + velocity.getY()*MAXSEEAHEAD*0.5);

        zombiePos = new Vector2D(position.getX() + width,
                position.getY() + height);

        avoidance = new Vector2D();


        return avoidance;
    }


    public Vector2D separation()
    {

        ArrayList<Zombie> neighbors = getNeighbours();

        Vector2D separationForce = new Vector2D();

        for(int i = 0; i < neighbors.size(); i++)
        {
            Zombie neighbour = neighbors.get(i);

            Vector2D fleeForce = flee(neighbour.getPosition());

            separationForce.setX(separationForce.getX() + fleeForce.getX());
            separationForce.setY(separationForce.getY() + fleeForce.getY());

        }

        return separationForce;

    }

    private ArrayList<Zombie> getNeighbours()
    {
        ArrayList <Zombie> neighbors = new ArrayList<Zombie>();

        for(int i = 0; i < handler.getWorld().getEntitymanager().getZombieAndBullets().size();  i++)
        {
            Entity e = handler.getWorld().getEntitymanager().getZombieAndBullets().get(i);

            if(e.equals(zombie))
                continue;


            if(e instanceof Zombie)
            {
                Zombie neighbour = (Zombie)e;

                if(distance(zombie.getCenter(), neighbour.getCenter()) < neighbour.getRadius()*2)
                {
                    neighbors.add(neighbour);
                }
            }
        }
        return neighbors;

    }

    private double distance(Vector2D a, Vector2D b)
    {

        double width = a.getX() - b.getX();
        double height = a.getY() - b.getY();

        return Math.sqrt(width*width + height*height);

    }

    public void render(Graphics g)
    {

        Graphics2D g2d =(Graphics2D)g;

        g2d.setStroke(new BasicStroke(5));

        g2d.setColor(Color.RED);
        int x = (int) handler.getGameCamera().getxOffset();
        int y = (int) handler.getGameCamera().getyOffset();

        Vector2D position = zombie.getPosition();
        Vector2D velocity = zombie.getVelocity();
        velocity = velocity.normalize();

        g.drawLine((int)(position.getX() - x+ zombie.getWidth()/2),  (int)(position.getY() - y + zombie.getHeight()/2),
                (int)(ahead.getX() - x),
                (int)(ahead.getY() - y));

        g2d.setColor(Color.magenta);

        g2d.drawLine((int)(position.getX() - x + zombie.getWidth()/2),  (int)(position.getY() - y + zombie.getHeight()/2),
                (int)(position.getX()  - x + zombie.getWidth()/2 + avoidance.getX()*20),
                (int)(position.getY() - y + zombie.getHeight()/2 + avoidance.getY()*20));

    }
}
