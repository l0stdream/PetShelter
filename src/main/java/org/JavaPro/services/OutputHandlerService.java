package org.JavaPro.services;

import java.io.PrintStream;

public class OutputHandlerService {
    private final PrintStream out;

    public OutputHandlerService(PrintStream out) {
        this.out = out;
    }

    public void printMessage(String message) {

        out.println(message);
    }

    public void printMessage(String format, Object... args) {
        out.printf(format, args);
    }

    public void printErrorMessage(String message) {
        System.err.println(message);
    }

}
