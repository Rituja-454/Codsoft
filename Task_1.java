import java.util.Random;
import java.util.Scanner;

    public class Task_1 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            boolean playAgain;
            int totalScore = 0;

            do {
                int lowerBound = 1;
                int upperBound = 100;
                int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                int maxAttempts = 7;
                int attempts = 0;
                boolean hasGuessedCorrectly = false;

                System.out.println("Welcome to Guess the Number!");
                System.out.println("I'm thinking of a number between " + lowerBound + " and " + upperBound + ".");
                System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

                while (attempts < maxAttempts && !hasGuessedCorrectly) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess == randomNumber) {
                        System.out.println("Congratulations! You guessed the number.");
                        hasGuessedCorrectly = true;
                        totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    } else if (userGuess < randomNumber) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }
                }

                if (!hasGuessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
                }

                System.out.println("Your score this round: " + (hasGuessedCorrectly ? (maxAttempts - attempts + 1) : 0));
                System.out.println("Total score: " + totalScore);

                System.out.print("Do you want to play again? (yes/no): ");
                playAgain = scanner.next().equalsIgnoreCase("yes");

            } while (playAgain);

            System.out.println("Thank you for playing! Your final score is " + totalScore + ".");
            scanner.close();
        }
    }


