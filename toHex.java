class HexadecimalConverter {

    public String toHex(int num) {

        // Agar number 0 hai
        if (num == 0) {
            return "0";
        }

        char[] hexChars = {
                '0', '1', '2', '3',
                '4', '5', '6', '7',
                '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f'
        };

        StringBuilder result = new StringBuilder();

        // Jab tak number 0 na ho
        while (num != 0) {

            // Last 4 bits lena
            int digit = num & 15;

            // Corresponding hex character add karna
            result.append(hexChars[digit]);

            // 4 bits right shift
            num >>>= 4;
        }

        // Reverse because digits ulte add hue the
        return result.reverse().toString();
    }

    public static void main(String[] args) {

        HexadecimalConverter obj = new HexadecimalConverter();

        System.out.println(obj.toHex(26));   // 1a
        System.out.println(obj.toHex(-1));   // ffffffff
        System.out.println(obj.toHex(0));    // 0
    }
}