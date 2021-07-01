package com.game2048;

import java.io.IOException;

import com.game2048.actionReader.ActionReader;
import com.game2048.actionReader.ActionReaderFactory;
import com.game2048.actionReader.exceptions.InvalidActionException;

public class App {

    public static void main(String[] args) throws IOException {
        ActionReader reader = ActionReaderFactory.create();
        GameController controller = new GameController();

        while (true) {
            try {
                controller.generateNewTile();
                controller.printBoard();
                System.out.println();

                ActionType at = reader.readNext();
                System.out.println(at);
                if (at == ActionType.UP) {
                    controller.slideTop();
                }
                else if (at == ActionType.DOWN) {
                    controller.slideBottom();
                }
                else if (at == ActionType.LEFT) {
                    controller.slideLeft();
                }
                else if (at == ActionType.RIGHT) {
                    controller.slideRight();
                }
            } catch (InvalidActionException e) {
                System.out.println("Invalid key, use the arrow keys to play");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
