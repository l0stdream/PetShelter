package org.JavaPro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.JavaPro.model.Animal;
import org.JavaPro.Executor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ShelterServiceTest {
    private Path tempDir;
    private File tempFile;
    private ShelterService shelterService;
    private ObjectMapper mapper;
    private InputHandlerService inputHandlerService;
    private Scanner scanner;
    private OutputHandlerService outputHandlerService;
    private AnimalInputService animalInputService;

    @Before
    public void loadInit() throws IOException {

        tempDir = Files.createTempDirectory("petShelterTest");
        tempFile = tempDir.resolve("animals.json").toFile();
        mapper = new ObjectMapper();
        outputHandlerService = new OutputHandlerService(System.out);


        if (!tempFile.exists()) {
            tempFile.createNewFile();
            mapper.writeValue(tempFile, List.of());
        }

    }

    @After
    public void flushTmp() {
        tempFile.delete();
        tempDir.toFile().delete();
    }

    @Test
    public void AddAnimalTest() throws IOException {

        String input = "Bobik\n1\nwolf\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        animalInputService = new AnimalInputService(inputHandlerService, outputHandlerService);
        shelterService = new ShelterService(mapper, tempDir, tempFile.toString(), inputHandlerService, outputHandlerService);


        Executor addAnimalExecutor = shelterService.addAnimal();
        addAnimalExecutor.execute();


        shelterService.save().execute();


        List<Animal> animals = mapper.readValue(tempFile, mapper.getTypeFactory().constructCollectionType(List.class, Animal.class));
        assertEquals(1, animals.size());
        assertEquals("Bobik", animals.get(0).getNickName());
    }

    @Test
    public void DeleteAnimalTest() throws IOException {

        Animal animal = new Animal("Bobik", 1, "wolf");
        mapper.writeValue(tempFile, List.of(animal));


        String input = "Bobik\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        animalInputService = new AnimalInputService(inputHandlerService, outputHandlerService);
        shelterService = new ShelterService(mapper, tempDir, tempFile.toString(), inputHandlerService, outputHandlerService);

        Executor deleteAnimal = shelterService.deleteAnimal();
        deleteAnimal.execute();


        shelterService.save().execute();


        List<Animal> resultList = mapper.readValue(tempFile, mapper.getTypeFactory().constructCollectionType(List.class, Animal.class));
        assertTrue(resultList.isEmpty());
    }

    @Test
    public void testSaveAnimals() throws IOException {


        String animal1 = "Bobik\n1\nwolf\n";
        createTestAnimal(animal1);
        saveTestAnimal();

        String animal2 = "Sharik\n123\ndog\n";
        createTestAnimal(animal2);
        saveTestAnimal();

        saveTestAnimal();


        List<Animal> savedAnimals = mapper.readValue(tempFile, mapper.getTypeFactory().constructCollectionType(List.class, Animal.class));
        assertEquals(2, savedAnimals.size());
        assertEquals("Bobik", savedAnimals.get(0).getNickName());
        assertEquals("Sharik", savedAnimals.get(1).getNickName());
    }

    private void setupShelterService(InputHandlerService inputHandlerService) {
        shelterService = new ShelterService(mapper, tempDir, tempFile.toString(), inputHandlerService, outputHandlerService);
    }

    private void createTestAnimal(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        setupShelterService(inputHandlerService);
        shelterService.addAnimal().execute();
    }

    private void saveTestAnimal() {
        Executor save = shelterService.save();
        save.execute();
    }
}
