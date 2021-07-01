package com.game2048.actionReader;

import java.util.Scanner;

import com.game2048.ActionType;
import com.game2048.actionReader.exceptions.InvalidActionException;

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
