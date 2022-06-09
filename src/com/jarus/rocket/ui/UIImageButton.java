package com.jarus.rocket.ui;

import com.jarus.rocket.states.MenuState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{

    protected BufferedImage images[];
    protected ClickListener clicker;

    public UIImageButton(float x,float y,int width,int height,BufferedImage images[],ClickListener clicker)
    {
        super(x,y,width,height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g, String name) {
        if ( (MenuState.btnclicked == 4) && (name.equals("QUIT"))
                || (MenuState.btnclicked == 3) && (name.equals("LOGOUT"))
                || (MenuState.btnclicked == 1) && (name.equals("PLAY")))
        {
            g.drawImage(images[2], (int)x, (int)y, width, height, null);
            return;
        }

        if (hovering)
        {
            g.drawImage(images[1], (int)x, (int)y, width, height, null);

        }
        else
            g.drawImage(images[0], (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
