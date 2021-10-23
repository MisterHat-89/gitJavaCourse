package com.company.forlearning.Lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.04.2021
 */

public class MainWindow extends JFrame {

    private final int WIN_WIDTH = 500;
    private final int WIN_HEIGHT = 560;
    private final int WIN_POS_X = 650;
    private final int WIN_POS_Y = 300;

    private Settings settings;
    private GameMap gameMap;


    MainWindow() {

//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension dimension = toolkit.getScreenSize();
//        int screenW = dimension.width;
//        int screenH = dimension.height;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POS_X, WIN_POS_Y);
//        setLocation(screenW / 2 - WIN_WIDTH / 2, screenH / 2 - WIN_HEIGHT / 2);
        setTitle("Game!!! Welcome player");
        setResizable(true);

        settings = new Settings(this);
        gameMap = new GameMap();

        JButton btnStart = new JButton("Start game");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });


        JButton btnExit = new JButton("Exit game");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(1, 2));
        panelForButtons.add(btnStart);
        panelForButtons.add(btnExit);

        add(panelForButtons, BorderLayout.SOUTH);
        add(gameMap);

//        setLayout(new GridLayout(20, 10));
//
//        for (int i = 0; i < 120; i++) {
//            add(new JButton("Button # " + i));
//        }

//        setLayout(new FlowLayout());
//        for (int i = 0; i < 20; i++) {
//            add(new JButton("Button # " + i));
//        }

        setVisible(true);
    }

    void getUserSettingAndStartGame(int gameMode, int gameMapSizeX, int gameMapSizeY, int winLength) {
        gameMap.startGame(gameMode, gameMapSizeX, gameMapSizeY, winLength);
    }

}
