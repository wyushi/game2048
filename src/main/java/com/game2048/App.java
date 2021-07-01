package com.game2048;

import java.io.IOException;

import com.game2048.actionReader.ActionReader;
import com.game2048.actionReader.ActionReaderFactory;
import com.game2048.gameDisplay.GameDisplay;
import com.game2048.gameDisplay.GameDisplayFactory;

public class App {

    public static void main(String[] args) throws IOException {
        ActionReader reader = ActionReaderFactory.create();
        GameDisplay display = GameDisplayFactory.create();
        GameManager manager = new GameManager(reader, display);
        manager.run();
    }
}
