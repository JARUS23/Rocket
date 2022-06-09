package com.jarus.rocket.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage background,mRocket,userlogodesign;
    public static BufferedImage mPlay[],mQuit[];
    public static BufferedImage mquit[];
    public static BufferedImage myes[],mno[];

    public static BufferedImage water,wall2,wall;
    public static BufferedImage bushes1,bushes2,bushes3,box;

    //PLAYER SPRITES
    public static BufferedImage player1,player2,player3,player4,player5;

    //PLAYER FACE LOGO
    public static BufferedImage playerfacelogo;

    //HEALTH LOGO
    public static BufferedImage healthlogo;

    //GUN LOGOS
    public static BufferedImage rocket_logo, machine_logo, plasma_logo, rail_logo, light_logo;
    public static BufferedImage logo_line;

    //ZOMBIES
    public static BufferedImage[] zombie = new BufferedImage[17];
    public static BufferedImage[] zombieAttack = new BufferedImage[9];

    //BLOOD
    public static BufferedImage blood;

    //How to Play
    public static BufferedImage guide;

    public static void init()
    {
        // Main Menu
        background = ImageLoader.loadImage("res/main_menu/main_menu_background.jpg");
        mRocket = ImageLoader.loadImage("res/main_menu/main_menu_rocket.png");
        userlogodesign = ImageLoader.loadImage("res/main_menu/user_logo_design.png");

        mPlay = new BufferedImage[2];
        mPlay[0] = ImageLoader.loadImage("res/main_menu/main_menu_play1.png");
        mPlay[1] = ImageLoader.loadImage("res/main_menu/main_menu_play2.png");

        mQuit = new BufferedImage[3];
        mQuit[0] = ImageLoader.loadImage("res/main_menu/main_menu_quit1.png");
        mQuit[1] = ImageLoader.loadImage("res/main_menu/main_menu_quit2.png");
        mQuit[2] = ImageLoader.loadImage("res/main_menu/main_menu_quit3.png");

        mquit = new BufferedImage[2];
        mquit[0] = ImageLoader.loadImage("res/main_menu/quit_game.png");
        mquit[1] = ImageLoader.loadImage("res/main_menu/quit_game.png");

        myes = new BufferedImage[2];
        myes[0] = ImageLoader.loadImage("res/main_menu/yes1.png");
        myes[1] = ImageLoader.loadImage("res/main_menu/yes2.png");

        mno = new BufferedImage[2];
        mno[0] = ImageLoader.loadImage("res/main_menu/no1.png");
        mno[1] = ImageLoader.loadImage("res/main_menu/no2.png");

        //How to Play
        guide = ImageLoader.loadImage("res/main_menu/howtoplay.png");

        //Player Sprite
        player1 = ImageLoader.loadImage("res/game/player.png");
        player2 = ImageLoader.loadImage("res/game/player2.png");
        player3 = ImageLoader.loadImage("res/game/player5.png");
        player4 = ImageLoader.loadImage("res/game/player3.png");
        player5 = ImageLoader.loadImage("res/game/player4.png");

        //PLAYER FACE LOGO
        playerfacelogo = ImageLoader.loadImage("res/game/face.png");

        //HEALTH LOGO
        healthlogo = ImageLoader.loadImage("res/game/health_logo.png");

        water = ImageLoader.loadImage("res/game/water.png");
        wall2 = ImageLoader.loadImage("res/game/wall7.jpg");
        wall = ImageLoader.loadImage("res/game/wall.jpg");
        bushes1 = ImageLoader.loadImage("res/game/bushes1.jpg");
        bushes2 = ImageLoader.loadImage("res/game/bushes2.jpg");
        bushes3 = ImageLoader.loadImage("res/game/bushes3.jpg");
        box = ImageLoader.loadImage("res/game/box.png");

        //GUN LOGOS
        machine_logo = ImageLoader.loadImage("res/game/machine_logo.jpg");
        rocket_logo = ImageLoader.loadImage("res/game/rocket_logo.jpg");
        plasma_logo = ImageLoader.loadImage("res/game/plasma_logo.jpg");
        light_logo = ImageLoader.loadImage("res/game/lightning_logo.jpg");
        rail_logo = ImageLoader.loadImage("res/game/rail_logo.jpg");
        logo_line = ImageLoader.loadImage("res/game/logo_line.png");

        //ZOMBIES
        for(int i = 0; i<zombie.length; i++)
            zombie[i] = ImageLoader.loadImage("res/game/Zombie/walk/"+i+".png");
        for(int i = 0; i<zombieAttack.length; i++)
            zombieAttack[i] = ImageLoader.loadImage("res/game/Zombie/attack/"+i+".png");

        //BLOOD
        blood = ImageLoader.loadImage("res/game/Zombie/blood/bloods.png");
    }
}
