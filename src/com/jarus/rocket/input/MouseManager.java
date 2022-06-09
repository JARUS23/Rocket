package com.jarus.rocket.input;

import com.jarus.rocket.game.Handler;
import com.jarus.rocket.ui.UIManager;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static boolean leftMouseButton = false;
    private static int x;
    private static int y;
    private int xMouse,yMouse;
    private UIManager uimanager;

    private int cnt = 1;
    public static int currentgun = 1;
    private Handler handler;

    public MouseManager(Handler handler)
    {
        this.handler = handler;
    }

    public void setUIManager(UIManager uimanager)
    {
        this.uimanager = uimanager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (uimanager != null)
            uimanager.onMousePressed(e);
        else if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftMouseButton = true;
            //AssetsSound.machinegun.play();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (uimanager != null)
            uimanager.onMouseRelease(e);

        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftMouseButton = false;

            //AssetsSound.machinegun.stop();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        xMouse = e.getX();
        yMouse = e.getY();

        if (uimanager != null)
            uimanager.onMouseMove(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int check = e.getWheelRotation();
        //System.out.println(e.getWheelRotation());

        if (check>0)
        {
            currentgun++;
            if (currentgun>8)
                currentgun = 1;
            //System.out.println(e.getWheelRotation() +" "+currentgun);
        }
        else
        {
            currentgun--;
            if (currentgun<1)
                currentgun = 8;
        }
    }

    //GETTERS AND SETTERS
    public static int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
