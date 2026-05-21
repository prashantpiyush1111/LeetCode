public class AddStringsSolution {
    public String addStrings(String num1, String num2) {
        StringBuilder str = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        int sum;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;

            sum = digit1 + digit2 + carry;
            str.append(sum % 10);
            carry = sum / 10;
        }

        return str.reverse().toString();
    }

    public static void main(String[] args) {
        AddStringsSolution solution = new AddStringsSolution();

        String num1 = "456";
        String num2 = "77";

        String result = solution.addStrings(num1, num2);

        System.out.println("Sum: " + result);
    }
}