package com.jarus.rocket.states;

import com.jarus.rocket.game.Display;
import com.jarus.rocket.game.Handler;
import com.jarus.rocket.gfx.Assets;
import com.jarus.rocket.gfx.AssetsSound;
import com.jarus.rocket.ui.ClickListener;
import com.jarus.rocket.ui.UIImageButton;
import com.jarus.rocket.ui.UIManager;

import java.awt.*;

public class MenuState extends State{

    public static boolean flag = false;
    public static int btnclicked = 0;
    private UIManager uimanager;

    public MenuState(Handler handler)
    {
        super(handler);
        uimanager = new UIManager(handler);


        // PLAY BUTTON
        handler.getMouseManager().setUIManager(uimanager);
        uimanager.addObject("PLAY", new UIImageButton(50,200,200,40, Assets.mPlay, new ClickListener(){

            @Override
            public void onClick() {
                AssetsSound.mbtnClicked.play();
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().guide);
                Display.createCursor2();
                //flag = true;
            }}));

        // QUIT BUTTON
        uimanager.addObject("QUIT",new UIImageButton(50,250,200,40,Assets.mQuit,new ClickListener(){

            @Override
            public void onClick() {
                AssetsSound.mbtnClicked.play();

                btnclicked = 4;
                //QUIT THE GAME?
                uimanager.addObject2("QUIT2",new UIImageButton(300,250,300,40,Assets.mquit,new ClickListener(){

                    @Override
                    public void onClick() {

                    }}));

                //YES
                uimanager.addObject2("YES",new UIImageButton(320,320,100,40,Assets.myes,new ClickListener(){

                    @Override
                    public void onClick() {
                        AssetsSound.mbtnClicked.play();
                        System.exit(0);
                    }}));

                //NO
                uimanager.addObject2("NO",new UIImageButton(440,320,100,40,Assets.mno,new ClickListener(){

                    @Override
                    public void onClick() {
                        AssetsSound.mbtnClicked.play();
                        flag = true;
                        btnclicked = 0;

                    }}));
            }}));
    }

    @Override
    public void tick() {
        uimanager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background,0,0,null);
        g.drawImage(Assets.mRocket,0,0,null);
        g.drawImage(Assets.userlogodesign,1339,22,null);
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.PLAIN, 25));

        uimanager.render(g);
    }
}
