package util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String ask(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static int askInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Please enter an valid integer");
            }
        }
    }
}
