package com.jarus.rocket.game;

import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.gfx.AssetsSound;
import com.jarus.rocket.gfx.GameCamera;
import com.jarus.rocket.input.KeyManager;
import com.jarus.rocket.input.MouseManager;
import com.jarus.rocket.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    private static Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //STATES
    public State menuState;
    public State guide;
    public State gameState1;
    public State gameover;
    public State timeup;

    //HANDLER
    private Handler handler;

    //INPUT
    private MouseManager mousemanager;
    private KeyManager keymanager;

    //Camera
    private GameCamera gameCamera;

    private int width,height;
    private boolean running = false;

    public Game()
    {
        keymanager = new KeyManager();
        mousemanager = new MouseManager(handler);
    }

    private void init()
    {
        Assets.init();
        AssetsSound.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int xsize = (int) toolkit.getScreenSize().getWidth();
        int ysize = (int) toolkit.getScreenSize().getHeight();
        width = xsize;
        height = ysize;
        System.out.println(xsize+" "+ysize);

        display = new Display(xsize,ysize);
        display.getFrame().addKeyListener(keymanager);

        display.getFrame().addMouseMotionListener(mousemanager);
        display.getFrame().addMouseListener(mousemanager);
        display.getFrame().addMouseWheelListener(mousemanager);

        display.getCanvas().addMouseMotionListener(mousemanager);
        display.getCanvas().addMouseListener(mousemanager);
        display.getCanvas().addMouseWheelListener(mousemanager);

        menuState = new MenuState(handler);
        guide = new Guide(handler);
        gameState1 = new GameState1(handler);
        gameover = new GameOver(handler);
        timeup = new TimeUp(handler);
        State.setState(menuState);

        AssetsSound.mbackground.play();
    }

    private void tick()
    {
        keymanager.tick();

        if (State.getState() != null)
            State.getState().tick();
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if (bs==null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0,0,width,height);
        // Draw Here

        if (State.getState() != null)
            State.getState().render(g);

        // End Draw
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;

        while(running)
        {
            now = System.nanoTime();
            delta = delta + (now - lastTime) / timePerTick;
            timer = timer + now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000)
            {
                System.out.println("Ticks and Frames - "+ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start()
    {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closethread()
    {
        thread.stop();
    }

    // GETTERS
    public MouseManager getMouseManager()
    {
        return mousemanager;
    }

    public KeyManager getKeyManager()
    {
        return keymanager;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public GameCamera getGameCamera()
    {
        return gameCamera;
    }
}
