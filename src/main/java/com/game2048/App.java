package com.game2048;

import java.io.IOException;

import com.game2048.display.GameDisplay;
import com.game2048.display.GameDisplayFactory;
import com.game2048.game.GameManager;
import com.game2048.reader.ActionReader;
import com.game2048.reader.ActionReaderFactory;

public class App {

    public static void main(String[] args) throws IOException {
        ActionReader reader = ActionReaderFactory.create();
        GameDisplay display = GameDisplayFactory.create();
        GameManager manager = new GameManager(reader, display);
        manager.run();
    }
}
