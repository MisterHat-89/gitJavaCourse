package com.company.forlearning.Lesson7;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.04.2021
 */

public class GameMap extends JPanel {

    public static final int PVE_MODE = 0;
    public static final int PVP_MODE = 1;

    GameMap() {
        setBackground(Color.BLACK);
    }

    void startGame(int gameMode, int gameMapSizeX, int gameMapSizeY, int winLength) {
        System.out.println("Game start with settings: [game mode = " + gameMode +
                "] [map size = " + gameMapSizeX + "x" + gameMapSizeY + "] [winLength = " + winLength + "]");

        setLayout(new GridLayout(gameMapSizeX, gameMapSizeY));

        for (int i = 0; i < gameMapSizeX * gameMapSizeX; i++) {
            add(new JButton("."));
        }

        setVisible(true);

    }

/*
1. Полностью разобраться с кодом (попробовать полностью самостоятельно переписать одно из окон)
2. * Раcчертить панель GameMap на поле для игры, согласно gameMapSizeX, gameMapSizeY
*/


}
