public class NumberOfDigitOne233 {

    // Counts the total number of digit '1' from 1 to n
    public static int countDigitOne(int n) {
        long factor = 1;
        int count = 0;

        while (factor <= n) {
            long lower = n % factor;
            long current = (n / factor) % 10;
            long higher = n / (factor * 10);

            if (current == 0) {
                count += higher * factor;
            } else if (current == 1) {
                count += higher * factor + lower + 1;
            } else {
                count += (higher + 1) * factor;
            }

            factor *= 10;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 13;

        int result = countDigitOne(n);

        System.out.println("Input : " + n);
        System.out.println("Output: " + result);
    }
}