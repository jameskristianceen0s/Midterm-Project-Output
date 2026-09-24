import java.util.Scanner;

public class ComprehensiveNumberAnalysisChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int snum, limit;
        int prime = 0, composite = 0;
        int odd = 0, even = 0;
        int palindrome = 0, armstrong = 0;
        int perfect = 0, abundant = 0, deficient = 0;
        
        int largestPrime = -1;
        int largestArmstrong = -1;
        int largestPerfect = -1;

        System.out.print("Enter a start number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            sc.close();
            return;
        }
        snum = sc.nextInt();

        System.out.print("Enter an end number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            sc.close();
            return;
        }
        limit = sc.nextInt();

        if (snum > limit) {
            System.out.println("The end number must be greater than or equal to the start number.");
            sc.close();
            return;
        }

        // Analyze each value once so every count refers to the same inclusive range.
        for (int i = snum; ; i++) {
            if (prime(i)) {
                prime++;
                if (i > largestPrime) {
                    largestPrime = i;
                }
            } else if (i > 1) {
                composite++;
            }

            if (i % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            
            // Palindrome and Armstrong checks are defined for non-negative values.
            if (i >= 0 && palindrome(i)) {
                palindrome++;
            }

            if (i >= 0 && armstrong(i)) {
                armstrong++;
                if (i > largestArmstrong) {
                    largestArmstrong = i;
                }
            }
            
            //The following conditions and steps to get the highest number of perfect, abundant, and deficient numbers in the range.
            if (i > 0) {
                int sum = sumOfProperDivisors(i);

                if (sum == i) {
                    perfect++;
                    if (i > largestPerfect) {
                        largestPerfect = i;
                    }
                } else if (sum > i) {
                    abundant++;
                } else {
                    deficient++;
                }
            }

            // This form avoids overflowing i when the end number is Integer.MAX_VALUE.
            if (i == limit) {
                break;
            }
        }
        
        System.out.println("\n==== NUMBER ANALYSIS REPORT ====");
        System.out.println();
        System.out.println("Range: " + snum + " - " + limit);
        System.out.println();
        System.out.println("Prime Numbers: " + prime);
        System.out.println("Composite Numbers: " + composite);
        System.out.println("Even Numbers: " + even);
        System.out.println("Odd Numbers: " + odd);
        System.out.println();
        System.out.println("Palindrome Numbers: " + palindrome);
        System.out.println("Armstrong Numbers: " + armstrong);
        System.out.println("Perfect Numbers: " + perfect);
        System.out.println("Abundant Numbers: " + abundant);
        System.out.println("Deficient Numbers: " + deficient);
        System.out.println();
        System.out.println("Largest Prime: " + largestPrime);
        System.out.println("Largest Perfect Number: " + largestPerfect);
        System.out.println("Largest Armstrong Number: " + largestArmstrong);

        sc.close();
    }

    // A prime number has exactly two positive divisors: 1 and itself.
    private static boolean prime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    // Reverse the digits and compare them with the original non-negative value.
    public static boolean palindrome(int n) {
        if (n < 0) {
            return false;
        }

        int temp = n;
        int reversed = 0;

        while (temp > 0) {
            reversed = (reversed * 10) + (temp % 10);
            temp /= 10;
        }

        return n == reversed;
    }

    // An Armstrong number equals the sum of each digit raised to the digit count.
    public static boolean armstrong(int n) {
        if (n < 0) {
            return false;
        }

        if (n == 0) {
            return true;
        }

        int temp = n;
        int digits = 0;
        long sum = 0;

        while (temp > 0) {
            digits++;
            temp /= 10;
        }

        temp = n;

        while (temp > 0) {
            int remainder = temp % 10;
            long power = 1;

            // Raise the digit to the required power using multiplication only.
            for (int i = 0; i < digits; i++) {
                power *= remainder;
            }

            sum += power;
            temp /= 10;
        }

        return n == sum;
    }

    // Add all positive divisors smaller than n; n itself is excluded.
    public static int sumOfProperDivisors(int n) {
        if (n <= 1) {
            return 0;
        }

        int sum = 1;

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                sum += i;

                if (i != n / i) {
                    sum += n / i;
                }
            }
        }

        return sum;
    }
}
