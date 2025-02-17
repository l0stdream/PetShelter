package org.JavaPro.services;

import org.JavaPro.enums.Messages;

import java.util.Scanner;

public class InputHandlerService {
    private final Scanner scanner;
    private final OutputHandlerService outputHandlerService;


    public InputHandlerService(Scanner scanner, OutputHandlerService outputHandlerService) {
        this.scanner = scanner;
        this.outputHandlerService = outputHandlerService;
    }


    public String readString() {
        return scanner.next();
    }


    public int readInt() {
        String inputText;
        while (true) {
            inputText = scanner.next();
            try {
                if (Integer.parseInt(inputText) > 0)
                    return Integer.parseInt(inputText);
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                outputHandlerService.printMessage(Messages.INPUT_NUMBERS_ONLY.getMessage());
            }
        }
    }

}
