package com.game2048.display;

public class GameDisplayFactory {
    public static GameDisplay create() {
        return new TerminalDisplay();
    }
}
