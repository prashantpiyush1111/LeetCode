public class AddStringsSolution {

    public static String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;

            int total = digit1 + digit2 + carry;
            result.insert(0, total % 10); // Changed: insert at beginning instead of append + reverse
            carry = total / 10;
        }

        return result.toString();
    }

    public static void testCase(String a, String b) { // Changed: helper method for multiple test cases
        System.out.println(a + " + " + b + " = " + addStrings(a, b));
    }
    public static void main(String[] args) {
        testCase("123", "789");
        testCase("999", "1");
        testCase("456", "77");
    }
}