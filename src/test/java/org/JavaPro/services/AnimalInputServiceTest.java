package org.JavaPro.services;

import org.JavaPro.model.Animal;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AnimalInputServiceTest {

    @Test
    public void createAnimalFromInputTest() {
        String input = "Bobik\n1\nwolf\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        OutputHandlerService outputHandlerService = new OutputHandlerService(System.out);
        InputHandlerService inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        AnimalInputService animalInputService = new AnimalInputService(inputHandlerService, outputHandlerService);

        Animal animal = animalInputService.createAnimal();
        assertEquals("Bobik", animal.getNickName());
        assertEquals(1, animal.getAge());
        assertEquals("wolf", animal.getBreed());
    }

}
