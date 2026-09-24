import java.util.Scanner;

public class MultiplicationTableMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
      System.out.print("Enter maximum number: ");
      if (!sc.hasNextInt()) {
         System.out.println("Please enter a valid integer.");
         sc.close();
         return;
      }
      int max = sc.nextInt();
        System.out.print("Enter the number of rows: ");
      if (!sc.hasNextInt()) {
         System.out.println("Please enter a valid integer.");
         sc.close();
         return;
      }
        int rows = sc.nextInt();
        System.out.print("Enter the number of columns: ");
      if (!sc.hasNextInt()) {
         System.out.println("Please enter a valid integer.");
         sc.close();
         return;
      }
        int columns = sc.nextInt();
        System.out.print("Enter starting number: ");
      if (!sc.hasNextInt()) {
         System.out.println("Please enter a valid integer.");
         sc.close();
         return;
      }
        int starting = sc.nextInt();

      if (max < 1 || rows < 1 || columns < 1 || starting < 1
            || starting + rows - 1 > max || starting + columns - 1 > max) {
         System.out.println("Maximum, rows, columns, and starting number must be positive.");
         System.out.println("The table values must also stay within the maximum.");
         sc.close();
         return;
      }
        
        int largestValue = 0;
      long sumOfValues = 0;
        int evenCount = 0;
        int oddCount = 0;
        
      // Print the column factors before the product rows.
      System.out.println("\n===== MULTIPLICATION TABLE =====");
      System.out.printf("%5s", " ");
      for (int column = 0; column < columns; column++) {
         System.out.printf("%5d", starting + column);
      }
      System.out.println();

      // Nested loops generate every product and update its statistics.
      for (int row = 0; row < rows; row++) {
         int rowFactor = starting + row;
         System.out.printf("%5d", rowFactor);

         for (int column = 0; column < columns; column++) {
            int columnFactor = starting + column;
            int product = rowFactor * columnFactor;
            System.out.printf("%5d", product);
            
            sumOfValues += product;
            if (product > largestValue) {
               largestValue = product;
            }
            if (product % 2 == 0) {
               evenCount++;
            } else {
               oddCount++;
            }
            }

         System.out.println();
      }

      System.out.println("\n===== STATISTICS =====");
        System.out.println("Largest value: " + largestValue);
        System.out.println("Sum of all values: " + sumOfValues);
        System.out.println("Number of even products: " + evenCount);
        System.out.println("Number of odd products: " + oddCount);

        sc.close();
   }
}
