package com.game2048.actionReader;

import com.game2048.ActionType;
import com.game2048.actionReader.exceptions.InvalidActionException;

public interface ActionReader {
    ActionType readNext() throws InvalidActionException;
}
