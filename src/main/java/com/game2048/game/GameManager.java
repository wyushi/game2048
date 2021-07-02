package com.game2048.game;

import com.game2048.display.GameDisplay;
import com.game2048.reader.ActionReader;
import com.game2048.reader.exceptions.InvalidActionException;

public class GameManager {

    private final ActionReader actionReader;
    private final GameDisplay display;
    private Game game;
    
    public GameManager(ActionReader actionReader, GameDisplay display) {
        this.actionReader = actionReader;
        this.display = display;
        this.game = new Game();
    }

    private Tile[][] copyBoard() {
        Tile[][] board = new Tile[game.board.length][];
        for (int i = 0; i < game.board.length; i++) {
            board[i] = new Tile[game.board[i].length];
            for (int j = 0; j < game.board[i].length; j++) {
                board[i][j] = game.board[i][j];
            }
        }
        return board;
    }

    public void run() {
        while (true) {
            try {
                game.generateNewTile();
                display.render(copyBoard());
                System.out.println();

                ActionType at = actionReader.readNext();
                System.out.println(at);
                if (at == ActionType.UP) {
                    game.slideTop();
                }
                else if (at == ActionType.DOWN) {
                    game.slideBottom();
                }
                else if (at == ActionType.LEFT) {
                    game.slideLeft();
                }
                else if (at == ActionType.RIGHT) {
                    game.slideRight();
                }
            } catch (InvalidActionException e) {
                System.out.println("Invalid key, use the arrow keys to play");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
