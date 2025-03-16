// import java.util.ArrayList;

public class Midterm1Review {

    // int sum; Var can't be outside scope of a static method

    public static int sumAll(int[] array) {
        int sum = 0; // Always need to initialize variables inside method. int's default value is 0 if it's an instance variable or 
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }


    public static Boolean isPalindrome(int[] a, int[]b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Error, arrays must be of same size");
        } else {
            for (int i = 0; i < a.length; i++) {
                int j = b.length;
                if (a[i] != b[j-i]) {
                    return false;
                } 
                j++;
            }
        return true;
        }
    }


    public static void main(String args[]) {
        int[] testArray = new int[3];
        testArray[0] = 1;
        testArray[1] = 2;
        testArray[2] = 3;
        System.out.println(sumAll(testArray));  // This only works if the method is static

        // ArrayPractice array = new ArrayPractice();
        // System.out.println(array.sumAll(testArray));
        // If I make a non static method, then the method belongs to an INSTANCE of the class, not the class itself.
    }
}
