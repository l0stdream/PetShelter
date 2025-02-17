package org.JavaPro.enums;

public enum Messages {
    ERROR("An unexpected error has occurred, we apologize. The program will close now."),
    INPUT_SHOWN_NUMBERS_ONLY("Please choose one of shown variants and type the answer as an integer."),
    MAIN_TEXT("Press 1 to see all the animals what are being in the shelter now;\n" +
            "Press 2 for add new pet to shelter;\n" +
            "Press 3 for for taking animal from the shelter;\n" +
            "Press 4 for exit. "),
    EXIT("Thank you for visiting us!"),
    HI("Hi and welcome to PetShelter v0.01!"),
    TAKE_ANIMAL("Which animal do you wanna take?" +
            "\nEnter animal name down below or press any key to exit to main menu."),
    INPUT_NUMBERS_ONLY("Please enter positive integers only."),
    ADD_ANIMAL_NAME("Please enter the name: "),
    ADD_ANIMAL_AGE("Enter pet age: "),
    ADD_ANIMAL_BREED("Enter pet breed: "),
    COULDNT_SAVE("Couldn't save the file.");


    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
