package com.jarus.rocket.ui;

import com.jarus.rocket.game.Handler;
import com.jarus.rocket.states.MenuState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    public ArrayList<UIObject> objects;
    public ArrayList<UIObject> objects2;
    public String name[];
    int cnt = 0,cnt2 = 0,ch;

    public UIManager(Handler handler)
    {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
        objects2 = new ArrayList<UIObject>();
        name = new String[10];
    }

    public void tick()
    {
        for (UIObject o : objects)
            o.tick();
    }

    public void render(Graphics g)
    {
        for (UIObject o : objects)
        {
            o.render(g,name[cnt2]);
            cnt2++;
        }
        cnt2 = 0;

        if (!objects2.isEmpty())
        {
            for (UIObject o : objects2)
            {
                o.render(g,name[0]);
            }
        }
    }

    public void onMouseMove(MouseEvent e)
    {
        if (!objects2.isEmpty())
        {
            for (UIObject o : objects2)
                o.onMouseMove(e);
        }
        else
        {
            for (UIObject o : objects)
                o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e)
    {
        if (MenuState.flag)
        {
            if (!objects2.isEmpty())
                objects2.clear();
            MenuState.flag = false;
        }
    }

    public void onMousePressed(MouseEvent e)
    {
        if (!objects2.isEmpty())
        {
            for (UIObject o : objects2)
            {
                o.onMousePressed(e);
            }
        }
        else
        {
            for (UIObject o : objects)
            {
                o.onMousePressed(e);
            }
        }
    }

    public void addObject(String name,UIObject o)
    {
        this.name[cnt] = name;
        objects.add(o);
        cnt++;
    }

    public void addObject2(String name,UIObject o)
    {
        objects2.add(o);
    }
}
