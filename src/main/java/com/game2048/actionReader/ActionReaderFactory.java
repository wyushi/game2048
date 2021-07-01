package com.game2048.actionReader;

public class ActionReaderFactory {

    public static ActionReader create() {
        return new TerminalActionReader();
    }
    
}
