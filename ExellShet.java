import java.util.*;
public class ExellShet {
    
    public static String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--;
            
            int remainder = columnNumber % 26;
            char ch = (char) ('A' + remainder);
            
            result.append(ch);
            
            columnNumber = columnNumber / 26;
        }
        
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        
        String ans = convertToTitle(n);
        System.out.println("Excel Column: " + ans);
        
        sc.close();
    }
}