package com.game2048.reader;

import java.util.Scanner;

import com.game2048.game.ActionType;
import com.game2048.reader.exceptions.InvalidActionException;

class TerminalActionReader implements ActionReader {

    Scanner input = new Scanner(System.in);

    @Override
    public ActionType readNext() throws InvalidActionException {
        String cmd = input.nextLine();
        if (cmd.equals("w")) {
            return ActionType.UP;
        }
        if (cmd.equals("s")) {
            return ActionType.DOWN;
        }
        if (cmd.equals("a")) {
            return ActionType.LEFT;
        }
        if (cmd.equals("d")) {
            return ActionType.RIGHT;
        }
        throw new InvalidActionException();
    }
    
}
