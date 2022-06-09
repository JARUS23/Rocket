package com.jarus.rocket.entities.creatures;

import com.jarus.rocket.entities.*;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.input.MouseManager;
import com.jarus.rocket.worlds.World1;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Creature {

    public static final int PLAYERSIZE = 80;
    public static final int MAXHEALTH = 100;

    public static Vector2D shootPoint;
    public static Vector2D creatureCenter;
    public static Vector2D newPoint;
    public static Vector2D bulletDirection;

    //BULLET OBJECT
    public static MachineBullet machinebullet;
    public static RocketBullet rocketbullet;
    public static PlasmaBullet plasmabullet;
    public static LightningBullet lightbullet;

    //GUB AMMO
    public static int machineammo = 300;
    public static int rocketammo = 100;
    public static int plasmaammo = 300;
    public static int lightammo = 350;

    private World1 world;

    public static boolean execute = true;
    float cnt = 6,cnt2 = 1;

    public Player(Vector2D position, double maxVelocity, World1 world)
    {
        super(world.getHandler(),position, PLAYERSIZE, PLAYERSIZE, maxVelocity, MAXHEALTH);
        this.velocity = new Vector2D(maxVelocity, maxVelocity);
        this.world = world;

        bounds.x = 22;
        bounds.y = 20;
        bounds.width = 35;
        bounds.height = 45;

        shootPoint = new Vector2D();
    }

    @Override
    public void tick()
    {
        int xx = (int) handler.getGameCamera().getxOffset();
        int yy = (int) handler.getGameCamera().getyOffset();

        //ANGLE
        double width = MouseManager.getX() - ((position.getX()-xx) + DEFAULT_CREATURE_WIDTH);
        double height = MouseManager.getY() - ((position.getY()-yy) + DEFAULT_CREATURE_HEIGHT);

        angle = Math.atan(height/width);

        if(width < 0)
            angle = -Math.PI + angle;

        //MOVEMENT
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);


        creatureCenter = new Vector2D(shootPoint.getX() - (position.getX() + DEFAULT_CREATURE_WIDTH/2),
                shootPoint.getY() - (position.getY() + DEFAULT_CREATURE_HEIGHT/2));

        newPoint = new Vector2D(creatureCenter.getX()*Math.cos(angle) - creatureCenter.getY()*Math.sin(angle),
                creatureCenter.getX()*Math.sin(angle) + creatureCenter.getY()*Math.cos(angle));

        shootPoint.setX(newPoint.getX() - xx + position.getX() + DEFAULT_CREATURE_WIDTH/2);
        shootPoint.setY(newPoint.getY() - yy + position.getY() + DEFAULT_CREATURE_HEIGHT/2);

        // NEED TO WORK ON IT
        if (MouseManager.leftMouseButton && (MouseManager.currentgun == 1) && (cnt%6==0.00) && (machineammo > 0))  //Machine Gun
        {
            bulletDirection = new Vector2D(Math.cos(angle), Math.sin(angle));
            bulletDirection = bulletDirection.normalize();

            machinebullet = new MachineBullet(world.getHandler(),shootPoint, bulletDirection,world);
            EntityManager.addZombieEntity(machinebullet);

            cnt=6;
            machineammo--;
        }
        else if (MouseManager.leftMouseButton && (MouseManager.currentgun == 3) && (cnt%6==0.00) && (rocketammo > 0)) //Rocket Bullet
        {
            bulletDirection = new Vector2D(Math.cos(angle), Math.sin(angle));
            bulletDirection = bulletDirection.normalize();

            rocketbullet = new RocketBullet(world.getHandler(),shootPoint, bulletDirection,world);
            EntityManager.addZombieEntity(rocketbullet);

            cnt=6;
            rocketammo--;
        }
        else if (MouseManager.leftMouseButton && (MouseManager.currentgun == 5) && (cnt%6==0.00) && (plasmaammo > 0)) //Plasma Bullet
        {
            bulletDirection = new Vector2D(Math.cos(angle), Math.sin(angle));
            bulletDirection = bulletDirection.normalize();

            plasmabullet = new PlasmaBullet(world.getHandler(),shootPoint, bulletDirection,world);
            EntityManager.addZombieEntity(plasmabullet);

            cnt=6;
            plasmaammo--;
        }
        else if (MouseManager.leftMouseButton && (MouseManager.currentgun == 7) && execute && (lightammo > 0)) //Lightning Bullet
        {
            bulletDirection = new Vector2D(Math.cos(angle), Math.sin(angle));
            bulletDirection = bulletDirection.normalize();

            lightbullet = new LightningBullet(world.getHandler(),shootPoint, bulletDirection,world);
            EntityManager.addZombieEntity(lightbullet);

            lightammo--;
        }


        cnt++;
        // update  shooting point
        shootPoint = new Vector2D(position.getX() + DEFAULT_CREATURE_WIDTH - 7, position.getY() + DEFAULT_CREATURE_HEIGHT - 27);
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g)
    {
        int xx = (int) handler.getGameCamera().getxOffset();
        int yy = (int) handler.getGameCamera().getyOffset();

        at = AffineTransform.getTranslateInstance(position.getX() - xx, position.getY() - yy);

        at.rotate(angle, DEFAULT_CREATURE_WIDTH/2, DEFAULT_CREATURE_HEIGHT/2);

        Graphics2D g2d = (Graphics2D)g;

        //Which Sprite
        if (MouseManager.currentgun == 1)
        {
            g2d.drawImage(Assets.player1,at,null);
        }
        else if (MouseManager.currentgun == 3)
        {
            g2d.drawImage(Assets.player2,at,null);
        }
        else if (MouseManager.currentgun == 5)
        {
            g2d.drawImage(Assets.player3,at,null);
        }
        else if (MouseManager.currentgun == 7)
        {
            g2d.drawImage(Assets.player4,at,null);
        }
    }

    public void hit()
    {
        health -= 20;
    }

    public World1 getWorld()
    {
        return world;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
}
