package com.game2048.reader;

public class ActionReaderFactory {

    public static ActionReader create() {
        return new TerminalActionReader();
    }
    
}
