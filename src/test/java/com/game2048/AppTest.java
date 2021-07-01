package com.game2048;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class AppTest {

    private static void checkRow(App app, int r, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(r, i), is(expect[i]));
        }
    }

    private static void checkCol(App app, int c, int[] expect) {
        for (int i = 0; i < 4; i++) {
            assertThat(app.get(i, c), is(expect[i]));
        }
    }

    @Test
    public void testInitEmptyBoard() {
        App app = new App();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testMoveToLeft() {
        App app = new App();
        app.put(0, 1, new Tile());
        app.put(0, 3, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 1, 0, 0});
    }

    @Test
    public void testMoveToLeft2() {
        App app = new App();
        app.put(0, 2, new Tile());
        app.moveToLeft(app.board[0]);
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMoveToRight() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 2, new Tile());
        app.moveToRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 0, 1, 1});
    }

    @Test
    public void testMerge() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.mergeTowardLeft(app.board[0]);
        assertThat(app.get(0, 0), is(1));
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testMerge2() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testMerge3() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 1, 0});
    }

    @Test
    public void testMerge4() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardLeft(app.board[0]);
        checkRow(app, 0, new int[] {2, 0, 2, 0});
    }

    @Test
    public void testMergeRgiht() {
        App app = new App();
        app.put(0, 0, new Tile(2));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.mergeTowardRight(app.board[0]);
        checkRow(app, 0, new int[] {0, 3, 0, 2});
    }

    @Test
    public void testNext() {
        App app = new App();
        app.slideLeft();
        for (int i = 0; i < 4; i++) {
            checkRow(app, i, new int[] {0, 0, 0, 0});
        }
    }

    @Test
    public void testNext2() {
        App app = new App();
        app.put(0, 1, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 0, 0, 0});
    }

    @Test
    public void testNext3() {
        App app = new App();
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 0, 0, 0});
    }

    @Test
    public void testNext4() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNext5() {
        App app = new App();
        app.put(0, 0, new Tile(1));
        app.put(0, 1, new Tile(2));
        app.put(0, 2, new Tile(3));
        app.put(0, 3, new Tile());
        app.slideLeft();
        checkRow(app, 0, new int[] {1, 2, 3, 1});
    }

    @Test
    public void testNextRight() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(0, 1, new Tile());
        app.put(0, 2, new Tile());
        app.put(0, 3, new Tile());
        app.slideRight();
        checkRow(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testNextTop() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideTop();
        checkCol(app, 0, new int[] {2, 2, 0, 0});
    }

    @Test
    public void testNextBottom() {
        App app = new App();
        app.put(0, 0, new Tile());
        app.put(1, 0, new Tile());
        app.put(2, 0, new Tile());
        app.put(3, 0, new Tile());
        app.slideBottom();
        checkCol(app, 0, new int[] {0, 0, 2, 2});
    }

    @Test
    public void testGenerateNewTile() {
        App app = new App();
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
