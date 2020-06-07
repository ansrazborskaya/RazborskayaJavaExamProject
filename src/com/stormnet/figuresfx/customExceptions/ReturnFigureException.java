package com.stormnet.figuresfx.customExceptions;

public class ReturnFigureException extends Exception {
    @Override
    public String getMessage() {
        return "No figures to return!";
    }
}
