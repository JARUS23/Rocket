package com.jarus.rocket.gfx;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class AssetsSound {

    public static MP3Player mbackground, mbtnHovering, mbtnClicked;

    public static void init()
    {
        mbackground = new MP3Player(new File("res/main_menu/sounds/background.mp3"));
        mbackground.setRepeat(true);
        mbtnHovering = new MP3Player(new File("res/main_menu/sounds/btn_hovering.mp3"));
        mbtnClicked = new MP3Player(new File("res/main_menu/sounds/btn_clicked.mp3"));
    }
}
