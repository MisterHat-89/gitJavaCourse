package com.company.forlearning.Lesson7;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 15.04.2021
 */

public class GameMap extends JPanel {

    public static final int PVE_MODE = 0;
    public static final int PVP_MODE = 1;
    private final Random RANDOM = new Random();

    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;

    private int gameMode;
    private int gameMapSizeX;
    private int gameMapSizeY;
    private int winLength;

    private int[][] map;

    private int cellWidth;
    private int cellHeight;

    private boolean isGameOver;
    private boolean isInitMap;

    private int stateGameOver;
    private final int STATE_DRAW = 0;
    private final int STATE_HUMAN_WIN = 1;
    private final int STATE_AI_WIN = 2;

    private final int PADDING_DOT = 5;

    private final String MSG_HUMAN_WIN = "HUMAN WIN!!!";
    private final String MSG_AI_WIN = "AI WIN!!!";
    private final String MSG_DRAW = "DRAW!!!";


    GameMap() {
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                gameUpdate(e);
            }
        });
        isInitMap = false;
    }

    void startGame(int gameMode, int gameMapSizeX, int gameMapSizeY, int winLength) {
        this.gameMode = gameMode;
        this.gameMapSizeX = gameMapSizeX;
        this.gameMapSizeY = gameMapSizeY;
        this.winLength = winLength;
        map = new int[gameMapSizeY][gameMapSizeX];
        isGameOver = false;
        isInitMap = true;
        repaint();
    }

    private void setGameOver(int stateGameOver) {
        isGameOver = true;
        this.stateGameOver = stateGameOver;
        repaint();
    }

    private void gameUpdate(MouseEvent mouse) {
        if (!isInitMap) return;
        if (isGameOver) return;

        int cellX = mouse.getX() / cellWidth;
        int cellY = mouse.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        map[cellY][cellX] = HUMAN_DOT;
        repaint();

        if (checkWin(HUMAN_DOT)) {
            setGameOver(STATE_HUMAN_WIN);
            return;
        }

        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }

        aiTurn();
        repaint();

        if (checkWin(AI_DOT)) {
            setGameOver(STATE_AI_WIN);
            return;
        }

        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }

    }

    private void render(Graphics g) {
        if (!isInitMap) return;

        int width = getWidth();
        int height = getHeight();

        cellWidth = width / gameMapSizeX;
        cellHeight = height / gameMapSizeY;

        g.setColor(Color.WHITE);

        drawLineMap(g, width, height);

        for (int y = 0; y < gameMapSizeY; y++) {
            for (int x = 0; x < gameMapSizeX; x++) {
                if (isEmptyCell(x, y)) continue;

                if (map[y][x] == HUMAN_DOT) {
                    g.setColor(Color.GREEN);
                    g.fillOval(x * cellWidth + PADDING_DOT, y * cellHeight + PADDING_DOT, cellWidth - 2 * PADDING_DOT, cellHeight - 2 *PADDING_DOT);
                } else if (map[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + PADDING_DOT, y * cellHeight + PADDING_DOT, cellWidth - 2 * PADDING_DOT, cellHeight - 2 *PADDING_DOT);
                } else {
                    showErrorMessage("Something wrong in render method: X = " + x + " Y = " + y);
                    return;
                }
            }
        }

        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        throw new RuntimeException(msg);
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight() / 3, getWidth(), getHeight() / 4);
        g.setColor(Color.ORANGE);
        Font fontLetter = new Font("Times new roman", Font.BOLD, 50);
        g.setFont(fontLetter);

        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 140, getHeight() / 2);
                break;
            case STATE_HUMAN_WIN:
                g.drawString(MSG_HUMAN_WIN, 50, getHeight() / 2);
                break;
            case STATE_AI_WIN:
                g.drawString(MSG_AI_WIN, 150, getHeight() / 2);
                break;
            default:
                showErrorMessage("Something wrong in showMessageGameOver method");
        }
    }

    private void drawLineMap(Graphics g, int width, int height) {
        for (int i = 0; i < gameMapSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        for (int i = 0; i < gameMapSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
    }

    public void aiTurn() {
        if (turnAIWinCell()) { //выиграет ли игрок на следующем ходу?
            return;
        }

        if (turnHumanWinCell()) { //выиграет ли комп на следующем ходу?
            return;
        }

        int x;
        int y;
        do {
            x = RANDOM.nextInt(gameMapSizeX);
            y = RANDOM.nextInt(gameMapSizeY);
        } while (!isEmptyCell(x, y));
        map[y][x] = AI_DOT;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < gameMapSizeY; i++) {
            for (int j = 0; j < gameMapSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    map[i][j] = AI_DOT;    // поставим нолик в каждую клетку поля по очереди
                    if (checkWin(AI_DOT)) {
                        return true;    // если мы выиграли, вернём истину, оставив нолик в выигрышной позиции
                    }
                    map[i][j] = EMPTY_DOT;    // если нет - вернём обратно пустоту в клетку и пойдём дальше
                }
            }
        }
        return false;
    }

    // Проверка, выиграет-ли игрок своим следующим ходом
    private boolean turnHumanWinCell() {
        for (int i = 0; i < gameMapSizeY; i++) {
            for (int j = 0; j < gameMapSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    map[i][j] = HUMAN_DOT;     // поставим крестик в каждую клетку по очереди
                    if (checkWin(HUMAN_DOT)) {    // если игрок победит
                        map[i][j] = AI_DOT;    // поставить на то место нолик
                        return true;
                    }
                    map[i][j] = EMPTY_DOT;    // в противном случае вернуть на место пустоту
                }
            }
        }
        return false;
    }

    // проверка на победу
    private boolean checkWin(int player) {
        for (int i = 0; i < gameMapSizeX; i++) {     // ползём по всему полю
            for (int j = 0; j < gameMapSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, player)) {
                    return true;    // проверим линию по х
                }
                if (checkLine(i, j, 1, 1, winLength, player)) {
                    return true;    // проверим по диагонали х у
                }
                if (checkLine(i, j, 0, 1, winLength, player)) {
                    return true;    // проверим линию по у
                }
                if (checkLine(i, j, 1, -1, winLength, player)) {
                    return true;    // проверим по диагонали х -у
                }
            }
        }
        return false;
    }

    // проверка линии
    private boolean checkLine(int x, int y, int vx, int vy, int len, int player) {
        final int farX = x + (len - 1) * vx;    // посчитаем конец проверяемой линии
        final int farY = y + (len - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;    // проверим не выйдет-ли проверяемая линия за пределы поля
        }
        for (int i = 0; i < len; i++) {    // ползём по проверяемой линии
            if (map[y + i * vy][x + i * vx] != player) {
                return false;    // проверим одинаковые-ли символы в ячейках
            }
        }
        return true;
    }

    public boolean isFullMap() {
        for (int i = 0; i < gameMapSizeY; i++) {
            for (int j = 0; j < gameMapSizeX; j++) {
                if (map[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < gameMapSizeX && y >= 0 && y < gameMapSizeY;
    }

    public boolean isEmptyCell(int x, int y) {
        return map[y][x] == EMPTY_DOT;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

}
