public class ConvertNumberToHexadecimal {
    public static String toHex(int num) {
        return Integer.toHexString(num);
    }

    public static void main(String[] args) {
        int num = 26;   // Example input
        System.out.println("Hexadecimal: " + toHex(num));
    }
}