package com.game2048.game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;


public class GameControllerTest {

    private static void checkRow(Game app, int r, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(r, i), is(expect[i]));
        }
    }

    private static void checkCol(Game app, int c, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(i, c), is(expect[i]));
        }
    }

    @Test
    public void testInitEmptyBoard() {
        Game app = new Game();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testMoveToLeft() {
        Game app = new Game();
        app.put(0, 1, new Tile());
        app.put(0, 3, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 1, 0, 0});
    }

    @Test
    public void testMoveToLeft2() {
        Game app = new Game();
        app.put(0, 2, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMoveToRight() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 2, new Tile());
        app.moveToRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 0, 1, 1});
    }

    @Test
    public void testMerge() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.mergeTowardLeft(app.board[0]);
        assertThat(app.get(0, 0), is(1));
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMerge2() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testMerge3() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 1, 0});
    }

    @Test
    public void testMerge4() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 2, 0});
    }

    @Test
    public void testMergeRgiht() {
        Game app = new Game();
        app.put(0, 0, new Tile(2));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 3, 0, 2});
    }

    @Test
    public void testNext() {
        Game app = new Game();
        app.slideLeft();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testNext2() {
        Game app = new Game();
        app.put(0, 1, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testNext3() {
        Game app = new Game();
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testNext4() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNext5() {
        Game app = new Game();
        app.put(0, 0, new Tile(1));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile(3));
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 2, 3, 1});
    }

    @Test
    public void testNextRight() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideRight();
        checkRow(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testNextTop() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideTop();
        checkCol(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNextBottom() {
        Game app = new Game();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideBottom();
        checkCol(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testGenerateNewTile() {
        Game app = new Game();
        for (int i = 0; i < 10; i++) {
            app.generateNewTile();
            app.slideLeft();
        }        
    }
}
