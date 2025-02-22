package org.JavaPro.services;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputHandlerServiceTest {
    private InputHandlerService inputHandlerService;
    private Scanner scanner;
    private OutputHandlerService outputHandlerService;

    @Test
    public void readStringTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("Bobik\n".getBytes());
        System.setIn(in);

        scanner = new Scanner(System.in);
        outputHandlerService = new OutputHandlerService(System.out);
        inputHandlerService = new InputHandlerService(scanner,outputHandlerService);

        String result = inputHandlerService.readString();
        assertEquals("Bobik", result);
    }

    @Test
    public void readIntValidInputTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes());
        System.setIn(in);

        scanner = new Scanner(System.in);
        outputHandlerService = new OutputHandlerService(System.out);
        inputHandlerService = new InputHandlerService(scanner,outputHandlerService);

        int result = inputHandlerService.readInt();
        assertEquals(5, result);
    }

    @Test
    public void readIntInvalidThenValidInputTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("abc\n123\n".getBytes());
        System.setIn(in);

        scanner = new Scanner(System.in);
        outputHandlerService = new OutputHandlerService(System.out);
        inputHandlerService = new InputHandlerService(scanner,outputHandlerService);

        int result = inputHandlerService.readInt();
        assertEquals(123, result);
    }


}
