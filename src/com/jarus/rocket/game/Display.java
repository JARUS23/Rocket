package com.jarus.rocket.game;

import javax.swing.*;
import java.awt.*;

public class Display {

    private static JFrame frame;
    private Canvas canvas;

    public Display(int xsize,int ysize)
    {
        frame = new JFrame();
        frame.setTitle("ROCKET");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/rocket_logo.png"));
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xsize, ysize);
        createCursor();
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(xsize,ysize));
        canvas.setMaximumSize(new Dimension(xsize,ysize));
        canvas.setMinimumSize(new Dimension(xsize,ysize));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public static void createCursor()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("res/cursor.png");
        Point point = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        frame.setCursor(cursor);
    }

    public static void createCursor2()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("res/cursor2.png");
        Point point = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        frame.setCursor(cursor);
    }

    public static void close()
    {
        frame.dispose();
    }

    // GETTERS
    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}
