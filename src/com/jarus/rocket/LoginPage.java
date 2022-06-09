package com.jarus.rocket;

import com.jarus.rocket.game.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private JPanel contentPane;
    private int xMouse,yMouse;

    public LoginPage()
    {
        createCursor();
        setTitle("ROCKET LOGIN");
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/rocket_logo.png"));
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Left Side Panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 320, 500);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("res/login/login_image.png"));
        lblNewLabel.setBounds(0, 0, 320, 500);
        panel.add(lblNewLabel);

        // Top Right Panel
        JPanel panel_1 = new JPanel();
        panel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        panel_1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse - 320,y - yMouse);
            }
        });
        panel_1.setBackground(new Color(0, 0, 51));
        panel_1.setBounds(320, 0, 480, 80);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // Close Buttons
        JLabel label_1 = new JLabel("");
        label_1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                label_1.setIcon(new ImageIcon("res/login/close_button.png"));
            }
        });
        label_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label_1.setIcon(new ImageIcon("res/login/close_button2.png"));
            }
        });
        label_1.setIcon(new ImageIcon("res/login/close_button2.png"));
        label_1.setBounds(460, 5, 15, 15);
        panel_1.add(label_1);

        // ROCKET logo Text
        JLabel label_2 = new JLabel("");
        label_2.setIcon(new ImageIcon("res/login/rocket_text.png"));
        label_2.setBounds(10, 11, 166, 58);
        panel_1.add(label_2);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(51, 0, 51));
        panel_2.setBounds(320, 80, 480, 420);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        // INFO
        String text = "<html> <div style='text-align: center;'>Rocket is a 2D Top Down Zombie Survival Game<br>" +
                "Zombies spawn automatically & you have to survive for 120 seconds.<br>" +
                "After killing zombies you earn points / score.<br><br>" +
                "Make sure you don't die & go kick some asses.<br><br>" +
                "Made by JARUS</div></html>";
        JLabel info = new JLabel(text);
        info.setBounds(70, 30, 350, 280);
        info.setForeground(Color.RED);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_2.add(info);

        // START Button
        JButton btnLogin = new JButton("START");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                game.start();
                dispose();
            }
        });
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setForeground(Color.WHITE);
                btnLogin.setBackground(Color.RED);
            }
        });
        btnLogin.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                btnLogin.setForeground(Color.RED);
                btnLogin.setBackground(new Color(51, 0, 51));
            }
        });
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.RED);
        btnLogin.setBorder(new LineBorder(Color.RED));
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogin.setBounds(142, 303, 197, 40);
        btnLogin.setFocusable(false);
        panel_2.add(btnLogin);
    }

    private void createCursor()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("res/cursor.png");
        Point point = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        setCursor(cursor);
    }
}
