package com.game2048.actionReader.exceptions;

public class ActionReaderException extends RuntimeException {
    
    public ActionReaderException() {
        super("action reader error");
    }

    public ActionReaderException(Throwable cause) {
        super("action reader error: ", cause);
    }

}
