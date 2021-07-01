package com.game2048.gameDisplay;

import com.game2048.Tile;

class TerminalDisplay implements GameDisplay {

    @Override
    public void render(Tile[][] board) {
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(" - ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    sb.append(board[i][j].level);
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
        
    }
    
}
