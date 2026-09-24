import java.util.Random;
import java.util.Scanner;

public class GuessingNumberGame {
    public static void main(String[] args) {

        // Random selects the secret number; Scanner reads the player's guesses.
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        // The player may make at most ten valid guesses in each game.
        int maxAttempts = 10;

        // Scalar variables maintain the multiple-game statistics; no array is needed.
        int totalGamesPlayed = 0;
        int totalGamesWon = 0;
        int totalGamesLost = 0;
        int bestScore = 0;
        int totalAttemptsAllGames = 0;

        // Variable used to determine whether the player wants another game
        int tryAgain;

        do {

            // Generate a new secret number from 1 through 100 for each round.
            int numberToGuess = random.nextInt(100) + 1;

            // Variables for the current game
            int guess = 0;
            int attempts = 0;
            int totalGuess = 0;

            // These values are updated only after a valid guess is entered.
            int highestGuess = Integer.MIN_VALUE;
            int lowestGuess = Integer.MAX_VALUE;

            // Track whether this round ended with a correct guess.
            boolean won = false;

            // Display the rules and limits before the round begins.
            System.out.println("\n==================================");
            System.out.println("   WELCOME TO GUESS THE NUMBER");
            System.out.println("==================================");
            System.out.println("Guess the number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts.");
            System.out.println();

            // Continue asking for guesses until the player wins
            // or reaches the maximum number of attempts
            while (attempts < maxAttempts) {

                System.out.print("Guess: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a whole number from 1 to 100.");
                    sc.next();
                    continue;
                }
                guess = sc.nextInt();

                // Out-of-range values are not valid guesses and do not use an attempt.
                if (guess < 1 || guess > 100) {
                    System.out.println("Guess must be between 1 and 100.");
                    continue;
                }

                // Count the current attempt
                attempts++;

                // Add the guess to the total for calculating the average
                totalGuess += guess;

                // Check and update the highest guess
                if (guess > highestGuess) {
                    highestGuess = guess;
                }

                // Check and update the lowest guess
                if (guess < lowestGuess) {
                    lowestGuess = guess;
                }

                // Compare the valid guess with the secret number.
                if (guess == numberToGuess) {
                    System.out.println("Correct! You guessed the number!");
                    won = true;
                    break;
                }

                // Give a directional hint after an incorrect guess.
                else if (guess > numberToGuess) {
                    System.out.println("Too high!");
                }

                else {
                    System.out.println("Too low!");
                }

                // Display remaining attempts
                if (attempts < maxAttempts) {
                    System.out.println("Attempts remaining: "
                            + (maxAttempts - attempts));
                }
            }

            // Reaching the attempt limit without a correct guess is a loss.
            if (!won) {
                System.out.println("\nMaximum attempts reached!");
                System.out.println("The secret number was: " + numberToGuess);
            }

            // Calculate the average of the valid guesses in this round.
            double averageGuess = (double) totalGuess / attempts;

            // Update the cumulative statistics for all completed rounds.
            totalGamesPlayed++;
            totalAttemptsAllGames += attempts;

            if (won) {
                totalGamesWon++;

                // A lower number of attempts is a better winning score.
                if (bestScore == 0 || attempts < bestScore) {
                    bestScore = attempts;
                }
            }
            else {
                totalGamesLost++;
            }

            // Display the required statistics for the completed round.
            System.out.println("\n========== GAME STATISTICS ==========");
            System.out.println("Secret Number: " + numberToGuess);
            System.out.println("Attempts: " + attempts);
            System.out.println("Highest Guess: " + highestGuess);
            System.out.println("Lowest Guess: " + lowestGuess);
            System.out.printf("Average Guess: %.2f%n", averageGuess);

            // Offer the multiple-round challenge after each completed round.
            do {
                System.out.println("\n1. Try Again");
                System.out.println("2. Exit");
                System.out.print("Choose: ");

                tryAgain = sc.nextInt();

                // Only 1 (play again) and 2 (exit) are valid choices.
                if (tryAgain != 1 && tryAgain != 2) {
                    System.out.println(
                        "Invalid choice, please enter 1 or 2."
                    );
                }

            } while (tryAgain != 1 && tryAgain != 2);

        } while (tryAgain == 1);

        // Calculate the average attempts across all completed games.
        double averageAttempts =
                (double) totalAttemptsAllGames / totalGamesPlayed;

        // Display the final multiple-game statistics.
        System.out.println("\n======================================");
        System.out.println("       OVERALL GAME STATISTICS");
        System.out.println("======================================");
        System.out.println("Total Games Played: " + totalGamesPlayed);
        System.out.println("Total Games Won: " + totalGamesWon);
        System.out.println("Total Games Lost: " + totalGamesLost);

        // A best score exists only when at least one game was won.
        if (bestScore > 0) {
            System.out.println("Best Score: " + bestScore + " attempts");
        }
        else {
            System.out.println("Best Score: No winning score yet");
        }

        System.out.printf("Average Attempts: %.2f%n", averageAttempts);

        sc.close();
    }
}
