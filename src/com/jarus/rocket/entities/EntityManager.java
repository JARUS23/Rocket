package com.jarus.rocket.entities;

import com.jarus.rocket.blood.Blood;
import com.jarus.rocket.entities.creatures.Player;
import com.jarus.rocket.game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {

    private Handler handler;
    private Player player;
    private static ArrayList<Entity> entities;
    private static ArrayList<Entity> zombiesAndBullets;
    private static ArrayList<Entity> guns;
    private ArrayList<Blood> zombieBlood;

    public EntityManager(Handler handler,Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        zombiesAndBullets = new ArrayList<Entity>();
        guns = new ArrayList<Entity>();
        zombieBlood = new ArrayList<Blood>();
    }

    public void tick()
    {
        player.tick();

        for(int i = 0; i < zombiesAndBullets.size(); i++)
            zombiesAndBullets.get(i).tick();

        for(int i = 0; i < guns.size(); i++)
            guns.get(i).tick();
    }

    public void render(Graphics g)
    {
        for (int i=0;i<zombieBlood.size();i++)
            zombieBlood.get(i).render(g);

        player.render(g);
        //System.out.println(entities.size());

        for(int i = 0; i < zombiesAndBullets.size(); i++)
            zombiesAndBullets.get(i).render(g); //System.out.println(zombie.size());

        for(int i = 0; i < guns.size(); i++)
            guns.get(i).render(g);
    }


    public static void addZombieEntity(Entity e)
    {
        zombiesAndBullets.add(e);
    }


    public static void removeZombieEntity(Entity e)
    {
        zombiesAndBullets.remove(e);
    }

    public void removeAll()
    {
        zombiesAndBullets.clear();
        zombieBlood.clear();
        guns.clear();
    }

    //GETTERS AND SETTERS
    public Player getPlayer()
    {
        return player;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public ArrayList<Entity> getZombieAndBullets()
    {
        return zombiesAndBullets;
    }

    public ArrayList<Blood> getZombieBlood()
    {
        return zombieBlood;
    }

    public ArrayList<Entity> getGuns()
    {
        return guns;
    }
}
