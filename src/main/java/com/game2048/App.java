package com.game2048;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class App {

    private static final int BOARD_SIZE = 4;

    Tile[][] board;
    Random ranGenerator = new Random(new Date().getTime());

    public App() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
    }

    public int get(int r, int c) {
        if (board[r][c] == null) return 0;
        return board[r][c].level;
    }

    public void put(int r, int c, Tile t) {
        board[r][c] = t;
    }

    public Tile[] getCol(int c) {
        Tile[] col = new Tile[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            col[i] = board[i][c];
        }
        return col;
    }

    private void setCol(int c, Tile[] col) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][c] =  col[i];
        }
    }

    public void slideLeft() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            Tile[] row = board[r];
            moveToLeft(row);
            mergeTowardLeft(row);
            moveToLeft(row);
        }
    }

    public void slideRight() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            Tile[] row = board[r];
            moveToRight(row);
            mergeTowardRight(row);
            moveToRight(row);
        }
    }

    public void slideTop() {
        for (int c = 0; c < BOARD_SIZE; c++) {
            Tile[] col = getCol(c);
            moveToLeft(col);
            mergeTowardLeft(col);
            moveToLeft(col);
            setCol(c, col);
        }
    }

    public void slideBottom() {
        for (int c = 0; c < BOARD_SIZE; c++) {
            Tile[] col = getCol(c);
            moveToRight(col);
            mergeTowardRight(col);
            moveToRight(col);
            setCol(c, col);
        }
    }

    public void mergeTowardLeft(Tile[] arr) {
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (arr[i] != null && arr[i - 1] != null &&
                arr[i].level == arr[i - 1].level) {
                Tile t = arr[i - 1];
                t.level++;
                arr[i] = null;
                i++;
            }
        }
    }

    public void mergeTowardRight(Tile[] arr) {
        for (int i = BOARD_SIZE - 2; i >= 0; i--) {
            if (arr[i] != null && arr[i + 1] != null &&
                arr[i].level == arr[i + 1].level) {
                Tile t = arr[i + 1];
                t.level++;
                arr[i] = null;
                i--;
            }
        }
    }

    public void moveToLeft(Tile[] arr) {
        int pos = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[i] != null) {
                if (pos != i) {
                    arr[pos] = arr[i];
                    arr[i] = null;
                }
                pos++;
            }
        }
    }

    public void moveToRight(Tile[] arr) {
        int pos = BOARD_SIZE - 1;
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            if (arr[i] != null) {
                if (pos != i) {
                    arr[pos] = arr[i];
                    arr[i] = null;
                }
                pos--;
            }
        }
    }


    public Tile generateNewTile() {
        Set<Integer> availiables = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == null) {
                    availiables.add(i * BOARD_SIZE + j);
                }
            }
        }
        if (availiables.isEmpty()) {
            return null;
        }
        int index = ranGenerator.nextInt(availiables.size());
        int next = new ArrayList<>(availiables).get(index);
        Tile t = new Tile();
        board[next / BOARD_SIZE][next % BOARD_SIZE] = t;
        return t;
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(" - ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != null) {
                    sb.append(board[i][j].level);
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }   
}
