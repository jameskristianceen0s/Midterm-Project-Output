import java.util.Scanner;

public class PrimeNumberAnalysis {

   // A prime number is greater than 1 and has no divisors other than 1 and itself.
   public static boolean isPrime(int n) {
      if (n < 2) {
         return false;
      }

      // n / i avoids overflow when checking large integer values.
      for (int i = 2; i <= n / i; i++) {
         if (n % i == 0) {
            return false;
         }
      }

      return true;
   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Enter N: ");
      if (!sc.hasNextInt()) {
         System.out.println("Please enter a valid integer.");
         sc.close();
         return;
      }
      int N = sc.nextInt();
      
      // Scalar variables track every required result; no array stores the primes.
      int totalPrimes = 0;
      int smallestPrime = -1;
      int largestPrime = -1;
      long sum = 0;
      
      // Keep only consecutive prime values to calculate the largest gap.
      int previousPrime = -1;
      int currentPrime = -1;
      int largestGap = 0;
      
      System.out.println("\n==== PRIME ANALYSIS ====");
      System.out.println("\nPrimes:");
      
      // Test and print every number from 2 through N.
      for (int i = 2; i <= N; i++) {
         if (isPrime(i)) {
            currentPrime = i;
            System.out.print(currentPrime + " ");
            totalPrimes++;

            sum += currentPrime;

            // The first prime encountered is the smallest.
            if (smallestPrime == -1) {
               smallestPrime = currentPrime;
            }

            // The loop is ascending, so the latest prime is the largest.
            largestPrime = currentPrime;

            // Compare consecutive primes and retain only the largest gap.
            if (previousPrime != -1) {
               int gap = currentPrime - previousPrime;

               if (gap > largestGap) {
                  largestGap = gap;
               }
            }

            previousPrime = currentPrime;
         }
      }

      System.out.println("\n");

      if (totalPrimes > 0) {

         double average = (double) sum / totalPrimes;

         System.out.println("Total primes: " + totalPrimes);
         System.out.println("Smallest prime: " + smallestPrime);
         System.out.println("Largest prime: " + largestPrime);
         System.out.println("Sum: " + sum);
         System.out.printf("Average: %.2f%n", average);
         System.out.println("Largest prime gap:\n" + largestGap);

      } else {
         System.out.println("No prime numbers found.");
      }

      sc.close();
   }
}
