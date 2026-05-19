public class StringMultiplierApp {

    public static void main(String[] args) {
        // Input values
        String num1 = "123";
        String num2 = "456";

        // Call function
        String result = multiplyStrings(num1, num2);

        // Output
        System.out.println("Result: " + result);   // 56088
    }

    // Function to multiply two numbers represented as strings
    public static String multiplyStrings(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int totalLength = l1 + l2;

        int[] result = new int[totalLength];

        int row = totalLength - 1;

        for (int i = l1 - 1; i >= 0; i--) {
            int carry = 0;
            int col = row;
            int digit1 = num1.charAt(i) - '0';

            for (int j = l2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                int sum = digit1 * digit2 + carry + result[col];

                result[col] = sum % 10;
                carry = sum / 10;
                col--;
            }

            while (carry > 0) {
                int sum = result[col] + carry;
                result[col] = sum % 10;
                carry = sum / 10;
                col--;
            }

            row--;
        }

        // Convert array to string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        boolean started = false;

        for (int digit : result) {
            if (digit != 0) {
                started = true;
            }
            if (started) {
                sb.append(digit);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}