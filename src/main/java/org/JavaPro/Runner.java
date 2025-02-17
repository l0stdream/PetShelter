package org.JavaPro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.JavaPro.enums.Messages;
import org.JavaPro.services.InputHandlerService;
import org.JavaPro.services.OutputHandlerService;
import org.JavaPro.services.ShelterService;

import java.nio.file.Path;

import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Path workDir = Path.of("src/main/resources");
        String fileName = "animals.json";
        Scanner scanner = new Scanner(System.in);
        OutputHandlerService outputHandlerService = new OutputHandlerService(System.out);
        InputHandlerService inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        JsonMapper jsonMapper = new JsonMapper();
        Map<Integer, Executor> orchestrator = init(jsonMapper, workDir, fileName, inputHandlerService, outputHandlerService);
        Integer choice;
        outputHandlerService.printMessage(Messages.HI.getMessage());
        do {
            outputHandlerService.printMessage(Messages.MAIN_TEXT.getMessage());
            choice = inputHandlerService.readInt();

            orchestrator
                    .getOrDefault(choice, () -> outputHandlerService.printMessage(Messages.INPUT_SHOWN_NUMBERS_ONLY.getMessage()))
                    .execute();

        } while (!choice.equals(4));
        outputHandlerService.printMessage(Messages.EXIT.getMessage());
        scanner.close();
    }


    private static Map<Integer, Executor> init(ObjectMapper mapper, Path workDir,
                                               String fileName, InputHandlerService inputHandlerService,
                                               OutputHandlerService outputHandlerService) {
        ShelterService shelterService = new ShelterService(mapper, workDir, fileName, inputHandlerService, outputHandlerService);
        return Map.of(
                1, shelterService.showAnimals(),
                2, shelterService.addAnimal(),
                3, shelterService.deleteAnimal(),
                4, shelterService.save()
        );
    }
}
