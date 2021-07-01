package com.game2048;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class GameControllerTest {

    private static void checkRow(GameController app, int r, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(r, i), is(expect[i]));
        }
    }

    private static void checkCol(GameController app, int c, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(i, c), is(expect[i]));
        }
    }

    @Test
    public void testInitEmptyBoard() {
        GameController app = new GameController();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testMoveToLeft() {
        GameController app = new GameController();
        app.put(0, 1, new Tile());
        app.put(0, 3, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 1, 0, 0});
    }

    @Test
    public void testMoveToLeft2() {
        GameController app = new GameController();
        app.put(0, 2, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMoveToRight() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 2, new Tile());
        app.moveToRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 0, 1, 1});
    }

    @Test
    public void testMerge() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.mergeTowardLeft(app.board[0]);
        assertThat(app.get(0, 0), is(1));
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMerge2() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testMerge3() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 1, 0});
    }

    @Test
    public void testMerge4() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 2, 0});
    }

    @Test
    public void testMergeRgiht() {
        GameController app = new GameController();
        app.put(0, 0, new Tile(2));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 3, 0, 2});
    }

    @Test
    public void testNext() {
        GameController app = new GameController();
        app.slideLeft();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testNext2() {
        GameController app = new GameController();
        app.put(0, 1, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testNext3() {
        GameController app = new GameController();
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testNext4() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNext5() {
        GameController app = new GameController();
        app.put(0, 0, new Tile(1));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile(3));
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 2, 3, 1});
    }

    @Test
    public void testNextRight() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideRight();
        checkRow(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testNextTop() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideTop();
        checkCol(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNextBottom() {
        GameController app = new GameController();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideBottom();
        checkCol(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testGenerateNewTile() {
        GameController app = new GameController();
        for (int i = 0; i < 10; i++) {
            app.generateNewTile();
            app.printBoard();
            System.out.println();
            app.slideLeft();
            app.printBoard();
            System.out.println("------");
        }        
    }
}
