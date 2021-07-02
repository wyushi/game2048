package com.game2048.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Game {

    private static final int BOARD_SIZE = 4;

    Tile[][] board;
    Random ranGenerator = new Random(new Date().getTime());

    public Game() {
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

    public boolean slideLeft() {
        boolean isMoved = false;
        for (int r = 0; r < BOARD_SIZE; r++) {
            Tile[] row = board[r];
            isMoved = moveToLeft(row) || isMoved;
            isMoved = mergeTowardLeft(row) || isMoved;
            isMoved = moveToLeft(row) || isMoved;
        }
        return isMoved;
    }

    public boolean slideRight() {
        boolean isMoved = false;
        for (int r = 0; r < BOARD_SIZE; r++) {
            Tile[] row = board[r];
            isMoved = moveToRight(row) || isMoved;
            isMoved = mergeTowardRight(row) || isMoved;
            isMoved = moveToRight(row) || isMoved;
        }
        return isMoved;
    }

    public boolean slideTop() {
        boolean isMoved = false;
        for (int c = 0; c < BOARD_SIZE; c++) {
            Tile[] col = getCol(c);
            isMoved = moveToLeft(col) || isMoved;
            isMoved = mergeTowardLeft(col) || isMoved;
            isMoved = moveToLeft(col) || isMoved;
            setCol(c, col);
        }
        return isMoved;
    }

    public boolean slideBottom() {
        boolean isMoved = false;
        for (int c = 0; c < BOARD_SIZE; c++) {
            Tile[] col = getCol(c);
            isMoved = moveToRight(col) || isMoved;
            isMoved = mergeTowardRight(col) || isMoved;
            isMoved = moveToRight(col) || isMoved;
            setCol(c, col);
        }
        return isMoved;
    }

    public boolean mergeTowardLeft(Tile[] arr) {
        boolean isMoved = false;
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (arr[i] != null && arr[i - 1] != null &&
                arr[i].level == arr[i - 1].level) {
                arr[i - 1] = new Tile(arr[i - 1].level + 1);
                arr[i] = null;
                i++;
                isMoved = true;
            }
        }
        return isMoved;
    }

    public boolean mergeTowardRight(Tile[] arr) {
        boolean isMoved = false;
        for (int i = BOARD_SIZE - 2; i >= 0; i--) {
            if (arr[i] != null && arr[i + 1] != null &&
                arr[i].level == arr[i + 1].level) {
                arr[i + 1] = new Tile(arr[i + 1].level + 1);
                arr[i] = null;
                i--;
                isMoved = true;
            }
        }
        return isMoved;
    }

    public boolean moveToLeft(Tile[] arr) {
        boolean isMoved = false;
        int pos = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (arr[i] != null) {
                if (pos != i) {
                    arr[pos] = arr[i];
                    arr[i] = null;
                    isMoved = true;
                }
                pos++;
            }
        }
        return isMoved;
    }

    public boolean moveToRight(Tile[] arr) {
        boolean isMoved = false;
        int pos = BOARD_SIZE - 1;
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            if (arr[i] != null) {
                if (pos != i) {
                    arr[pos] = arr[i];
                    arr[i] = null;
                    isMoved = true;
                }
                pos--;
            }
        }
        return isMoved;
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

}
