package org.JavaPro.services;

import org.JavaPro.enums.Messages;
import org.JavaPro.model.Animal;


public class AnimalInputService {
    private final InputHandlerService inputHandlerService;
    private final OutputHandlerService outputHandlerService;

    public AnimalInputService(InputHandlerService inputHandlerService, OutputHandlerService outputHandlerService) {
        this.inputHandlerService = inputHandlerService;
        this.outputHandlerService = outputHandlerService;
    }

    public Animal createAnimal() {
        Animal.AnimalBuilder builder = Animal.builder();

        outputHandlerService.printMessage(Messages.ADD_ANIMAL_NAME.getMessage());
        builder.nickName(inputHandlerService.readString());

        outputHandlerService.printMessage(Messages.ADD_ANIMAL_AGE.getMessage());
        builder.age(inputHandlerService.readInt());

        outputHandlerService.printMessage(Messages.ADD_ANIMAL_BREED.getMessage());
        builder.breed(inputHandlerService.readString());

        return builder.build();
    }
}