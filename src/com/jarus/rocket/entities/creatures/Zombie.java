package com.jarus.rocket.entities.creatures;

import com.jarus.rocket.blood.Blood;
import com.jarus.rocket.entities.Vector2D;
import com.jarus.rocket.gfx.Animation;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.worlds.World1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class Zombie extends Creature{

    public static final int ZOMBIESIZE = 128;
    public static final int MAXHEALTH = 100;

    private double mass;
    private Steering steering;
    private Player player;
    private Vector2D acceleration, heading;
    private double maxForce;

    //ANIMATION
    private Animation walkAnimation, attackAnimation;
    private Animation currentAnimation;

    private Timer attackDelay;

    private boolean attacking = false;

    private double distanceToPlayer = 0;

    public Zombie(Vector2D position, double maxVelocity, Player player)
    {
        super(player.getWorld().getHandler(), position, ZOMBIESIZE, ZOMBIESIZE, maxVelocity, MAXHEALTH);
        this.player = player;
        steering = new Steering(this, player, handler);
        acceleration = new Vector2D();
        heading = new Vector2D();
        mass = 5;
        maxForce = 5;


        attackDelay = new Timer(350, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(distanceToPlayer < Player.DEFAULT_CREATURE_HEIGHT)
                {
                    player.hit();
                }

                attackDelay.stop();
                attacking = false;
                currentAnimation = walkAnimation;
            }
        });


        walkAnimation = new Animation(20, Assets.zombie);
        attackAnimation = new Animation(35,Assets.zombieAttack);
        currentAnimation = walkAnimation;
    }

    @Override
    public void tick()
    {
        int xx = (int) handler.getGameCamera().getxOffset();
        int yy = (int) handler.getGameCamera().getyOffset();

        bounds = new Rectangle((int)position.getX()-xx+25,(int)position.getY()-yy+25,(int)ZOMBIESIZE - 50,(int)ZOMBIESIZE - 50);

        Vector2D seekForce = steering.seek(new Vector2D(player.getPosition().getX(), player.getPosition().getY()));
        Vector2D avoidanceForce = steering.obstacleAvoidance();
        Vector2D separationForce = steering.separation();

        Vector2D steeringForce = new Vector2D();


        if(!avoidanceForce.isZero())
            seekForce = new Vector2D();


        steeringForce.setX(seekForce.getX() + avoidanceForce.getX() + separationForce.getX());
        steeringForce.setY(seekForce.getY() + avoidanceForce.getY() + separationForce.getY());

        steeringForce.divideByScalar(10);

        steeringForce.truncate(maxForce);

        acceleration.setX(acceleration.getX() + steeringForce.getX());
        acceleration.setY(acceleration.getY() + steeringForce.getY());

        acceleration.divideByScalar(mass);

        velocity.setX(velocity.getX() + acceleration.getX());
        velocity.setY(velocity.getY() + acceleration.getY());
        //System.out.println(acceleration.getX()+" "+acceleration.getY());

        velocity.truncate(2.0f);

        Vector2D toPlayer = player.getPosition().substract(position);
        distanceToPlayer = toPlayer.getMagnitude();

        if(distanceToPlayer < Player.DEFAULT_CREATURE_HEIGHT/2 && !attackDelay.isRunning())
        {
            attacking = true;
            attackDelay.start();
            currentAnimation = attackAnimation;
            currentAnimation.setIndex();
        }

        if(attacking)
            velocity.zero();

        position.setX(position.getX() + velocity.getX());
        position.setY(position.getY() + velocity.getY());
        //System.out.println(position.getX()+" "+position.getY());


        if(velocity.getMagnitude() > 0){
            heading = velocity.normalize();

            angle = Math.acos(heading.dot(new Vector2D(1,0)));
            if(heading.getY() < 0)
                angle *= -1;
        }


        currentAnimation.tick();
    }

    @Override
    public void render(Graphics g)
    {
        int xx = (int) handler.getGameCamera().getxOffset();
        int yy = (int) handler.getGameCamera().getyOffset();

        at = AffineTransform.getTranslateInstance(position.getX() - xx, position.getY() - yy);

        at.rotate(angle, ZOMBIESIZE/2, ZOMBIESIZE/2);

        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);

        //steering.render(g2d);
    }

    public Vector2D getCenter()
    {
        return new Vector2D(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
    }

    public double getRadius()
    {
        return bounds.width/2;
    }

    public double getMaxForce()
    {
        return maxForce;
    }

    //DAMAGE BY BULLET METHODS
    public void machinehit()
    {
        health -= 20;
        if(health <= 0){
            player.getWorld().getEntitymanager().getZombieBlood().add(new Blood(position, player.getWorld()));
            player.getWorld().getEntitymanager().getZombieAndBullets().remove(this);
        }
        World1.score = World1.score + 10;
    }

    public void rockethit()
    {
        health -= 50;
        if(health <= 0){
            player.getWorld().getEntitymanager().getZombieBlood().add(new Blood(position, player.getWorld()));
            player.getWorld().getEntitymanager().getZombieAndBullets().remove(this);
        }
        World1.score = World1.score + 20;
    }

    public void plasmahit()
    {
        health -= 30;
        if(health <= 0){
            player.getWorld().getEntitymanager().getZombieBlood().add(new Blood(position, player.getWorld()));
            player.getWorld().getEntitymanager().getZombieAndBullets().remove(this);
        }
        World1.score = World1.score + 15;
    }

    public void lighthit()
    {
        health -= 10;
        if(health <= 0){
            player.getWorld().getEntitymanager().getZombieBlood().add(new Blood(position, player.getWorld()));
            player.getWorld().getEntitymanager().getZombieAndBullets().remove(this);
        }
        World1.score = World1.score + 7;
    }
}
