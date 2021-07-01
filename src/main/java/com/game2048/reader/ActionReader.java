package com.game2048.reader;

import com.game2048.game.ActionType;
import com.game2048.reader.exceptions.InvalidActionException;

public interface ActionReader {
    ActionType readNext() throws InvalidActionException;
}
