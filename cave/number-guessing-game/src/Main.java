import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        java.util.Random rand = new java.util.Random();
        int i = rand.nextInt(100);
        boolean ifAnswerCorrect = false;
        Scanner sc = new Scanner(System.in);

        while (!ifAnswerCorrect) {
            System.out.println("Please guess the number..");
            int guess = sc.nextInt();
            if (guess == i) {
                ifAnswerCorrect = true;
                System.out.println("Congratulations! You guessed correctly.");
            } else {
                if (guess > i) {
                    System.out.println("You guessed the number greater than the number you guessed.");
                } else {
                    System.out.println("You guessed the number less than the number you guessed.");
                }
            }
        }

        System.out.println("Thanks for playing. Goodbye!");

    }
}