public class StringToIntegerConverter {

    public static int myAtoi(String s) {

        int isPositive = 1;
        StringBuilder finalValue = new StringBuilder("");
        s = s.trim();

        if (s.isEmpty()) {
            return 0;
        }

        if (s.charAt(0) == '-' || s.charAt(0) == '+' || Character.isDigit(s.charAt(0))) {
            int startPosition = 0;

            if (s.charAt(0) == '-') {
                isPositive = -1;
            }

            startPosition = Character.isDigit(s.charAt(0)) ? 0 : 1;

            for (char c : s.substring(startPosition).toCharArray()) {
                if (Character.isDigit(c)) {
                    if (finalValue.length() > 0 && c == '0') {
                        finalValue.append(c);
                    } else if (c != '0') {
                        finalValue.append(c);
                    }
                } else {
                    break;
                }
            }

            if (finalValue.length() > 0) {
                if (finalValue.length() <= 10) {
                    long returnVal = Long.parseLong(finalValue.toString()) * isPositive;

                    if (returnVal > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    } else if (returnVal < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    } else {
                        return (int) returnVal;
                    }
                } else if (finalValue.length() > 10 && isPositive == 1) {
                    return Integer.MAX_VALUE;
                } else if (finalValue.length() > 10) {
                    return Integer.MIN_VALUE;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String input = "   -42";
        System.out.println(myAtoi(input));
    }
}