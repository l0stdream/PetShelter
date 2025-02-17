package org.JavaPro.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.JavaPro.enums.Messages;
import org.JavaPro.model.Animal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.JavaPro.Executor;

public class ShelterService {
    private List<Animal> animals;
    private final ObjectMapper mapper;
    private final Path workDir;
    private final File shelter;
    private final InputHandlerService inputHandlerService;
    private final OutputHandlerService outputHandlerService;
    private final AnimalInputService animalInputService;

    public ShelterService(ObjectMapper mapper, Path workDir, String fileName, InputHandlerService inputHandlerService, OutputHandlerService outputHandlerService) {

        this.mapper = mapper;
        this.workDir = workDir;
        this.shelter = this.workDir.resolve(fileName).toFile();
        this.inputHandlerService = inputHandlerService;
        this.outputHandlerService = outputHandlerService;
        this.animalInputService = new AnimalInputService(this.inputHandlerService, this.outputHandlerService);
        try {
            if (!shelter.exists()) {
                shelter.createNewFile();
                mapper.writeValue(shelter, List.of());
            }
            this.animals = mapper.readValue(shelter, new TypeReference<>() {
            });

        } catch (IOException e) {
            this.outputHandlerService.printErrorMessage(Messages.ERROR.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }


    public Executor addAnimal() {
        return () -> this.animals.add(animalInputService.createAnimal());
    }

    public Executor showAnimals() {
        return () -> {
            outputHandlerService.printMessage("=== Animals list ===");
            IntStream.range(0, animals.size()).forEach(i ->
                    outputHandlerService.printMessage("%d) NickName: %s | Age: %d | Breed: %s%n",
                            i + 1, animals.get(i).getNickName(), animals.get(i).getAge(), animals.get(i).getBreed())
            );
            outputHandlerService.printMessage("====================");
        };
    }


    public Executor deleteAnimal() {
        return () -> {
            outputHandlerService.printMessage(Messages.TAKE_ANIMAL.getMessage());
            showAnimals().execute();
            String name = inputHandlerService.readString();
            animals.removeIf(animal -> Objects
                    .equals(name, animal.getNickName()));
        };
    }


    public Executor save() {
        return () -> {
            try {
                mapper.writeValue(shelter, animals);
            } catch (IOException e) {
                outputHandlerService.printErrorMessage(Messages.COULDNT_SAVE.getMessage());
                e.printStackTrace();
            }
        };
    }

}
