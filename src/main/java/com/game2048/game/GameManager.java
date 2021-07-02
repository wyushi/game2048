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
        boolean anyTileMoved = true;
        while (true) {
            try {
                if (anyTileMoved) {
                    game.generateNewTile();
                }
                display.render(copyBoard());
                System.out.println();

                ActionType at = actionReader.readNext();
                System.out.println(at);
                if (at == ActionType.UP) {
                    anyTileMoved = game.slideTop();
                }
                else if (at == ActionType.DOWN) {
                    anyTileMoved = game.slideBottom();
                }
                else if (at == ActionType.LEFT) {
                    anyTileMoved = game.slideLeft();
                }
                else if (at == ActionType.RIGHT) {
                    anyTileMoved = game.slideRight();
                }
            } catch (InvalidActionException e) {
                anyTileMoved = false;
                System.out.println("Invalid key, use the arrow keys to play");
            } catch (Exception e) {
                anyTileMoved = false;
                e.printStackTrace();
            }
        }
    }
}
