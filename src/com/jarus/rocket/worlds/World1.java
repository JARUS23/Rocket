package com.jarus.rocket.worlds;

import com.jarus.rocket.entities.EntityManager;
import com.jarus.rocket.entities.Vector2D;
import com.jarus.rocket.entities.creatures.Player;
import com.jarus.rocket.entities.creatures.Zombie;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.input.MouseManager;
import com.jarus.rocket.states.State;
import com.jarus.rocket.tiles.Tile;
import com.jarus.rocket.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class World1 {

    private Handler handler;
    private int width,height;
    private int spawnX,spawnY;
    private int tiles[][];
    private int cnt = 1;

    public static int score = 0;

    private int gametime;

    private boolean execute = true;

    //private Bullet bullet;
    //private boolean execute = true;

    //Entities
    private EntityManager entitymanager;

    // ZOMBIES SPAWN TIME
    private Timer timer = new Timer(1800, new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            entitymanager.getZombieAndBullets().add(new Zombie(new Vector2D(1500,200),2,entitymanager.getPlayer()));
            entitymanager.getZombieAndBullets().add(new Zombie(new Vector2D(200,1500),2,entitymanager.getPlayer()));
            entitymanager.getZombieAndBullets().add(new Zombie(new Vector2D(2000,1500),2,entitymanager.getPlayer()));
            entitymanager.getZombieAndBullets().add(new Zombie(new Vector2D(1700,1000),2,entitymanager.getPlayer()));
        }

    });

    // GAME CLOCK
    private Timer gameclock = new Timer(1000, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gametime <= 0)
                State.setState(handler.getGame().timeup);
            gametime = gametime - 1;
        }
    });

    public World1(Handler handler,String path)
    {
        this.handler = handler;
        entitymanager = new EntityManager(handler, new Player(new Vector2D(100,100),4,this));
        entitymanager.getZombieAndBullets().clear();

        loadworld(path);

        gametime = 120;

        entitymanager.getPlayer().getPosition().setX(spawnX);
        entitymanager.getPlayer().getPosition().setY(spawnY);
    }

    public void tick()
    {
        if (execute)
        {
            timer.start();
            gameclock.start();
            execute = false;
        }

        if (entitymanager.getPlayer().getHealth() < 1)
        {
            State.setState(handler.getGame().gameover);
        }
        entitymanager.tick();
    }

    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width,(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height,(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y=yStart;y<yEnd;y++)
            for (int x=xStart;x<xEnd;x++)
            {
                getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }

        //ENTITIES
        entitymanager.render(g);


        // GAME TIME LEFT
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 22));
        g.drawString("TIME LEFT : "+gametime+" SEC", 610, 30);


        //GUN LOGOS
        g.drawImage(Assets.machine_logo,50,670,null);
        g.drawImage(Assets.rocket_logo,120,670,null);
        g.drawImage(Assets.plasma_logo,190,670,null);
        g.drawImage(Assets.light_logo,260,670,null);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.drawString(Player.machineammo+"", 55, 740);
        g.drawString(Player.rocketammo+"", 125, 740);
        g.drawString(Player.plasmaammo+"", 195, 740);
        g.drawString(Player.lightammo+"", 265, 740);

        //FACE AND HEALTH
        g.drawImage(Assets.playerfacelogo,1200,650,null);


        //RED LINE
        g.setColor(Color.RED);
        g.drawImage(Assets.logo_line,1080,720,null);
        g.drawImage(Assets.logo_line,1128,720,null);
        g.drawImage(Assets.logo_line,1176,720,null);


        //HEALTH BAR
        int health = entitymanager.getPlayer().getHealth();
        g.setColor(Color.WHITE);
        if (health>=75)
        {
            g.fillRect(1080, 705, 20, 7);
            g.fillRect(1105, 705, 20, 7);
            g.fillRect(1130, 705, 20, 7);
            g.fillRect(1155, 705, 20, 7);
        }
        else if (health>=50)
        {
            g.fillRect(1105, 705, 20, 7);
            g.fillRect(1130, 705, 20, 7);
            g.fillRect(1155, 705, 20, 7);
        }
        else if (health>=25)
        {
            g.setColor(Color.BLUE);
            g.fillRect(1130, 705, 20, 7);
            g.fillRect(1155, 705, 20, 7);
        }
        else if (health>=0)
        {
            g.setColor(Color.RED);
            g.fillRect(1155, 705, 20, 7);
        }


        //HEALTH LOGO
        g.drawImage(Assets.healthlogo,1183,675,null);


        //REAL HEALTH
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.drawString(health+"/100",1090,695);

        //GUN LOGO LINE
        if (MouseManager.currentgun == 1)
            g.drawImage(Assets.logo_line,50,750,null);
        else if (MouseManager.currentgun == 3)
            g.drawImage(Assets.logo_line,120,750,null);
        else if (MouseManager.currentgun == 5)
            g.drawImage(Assets.logo_line,190,750,null);
        else if (MouseManager.currentgun == 7)
            g.drawImage(Assets.logo_line,260,750,null);
    }

    public Tile getTile(int x,int y)
    {
        if (x<0 || y<0 || x>=width || y>=height)
            return Tile.tile1;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t==null)
            return Tile.tile1;
        return t;
    }

    private void loadworld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String tokens[] = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y=0;y<height;y++)
            for (int x=0;x<width;x++)
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
    }

    //GETTERS
    public Handler getHandler()
    {
        return handler;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public EntityManager getEntitymanager()
    {
        return entitymanager;
    }
}
