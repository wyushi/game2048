package com.game2048.gameDisplay;

public class GameDisplayFactory {
    public static GameDisplay create() {
        return new TerminalDisplay();
    }
}
