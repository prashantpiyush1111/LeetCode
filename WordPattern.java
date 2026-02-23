import java.util.*;

public class WordPattern {

    public static boolean wordPattern(String pattern, String s) {
        
        String[] words = s.split(" ");
        
        // Length check
        if (pattern.length() != words.length) {
            return false;
        }
        
        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            
            char ch = pattern.charAt(i);
            String word = words[i];
            
            // Check char -> word mapping
            if (map1.containsKey(ch)) {
                if (!map1.get(ch).equals(word)) {
                    return false;
                }
            } else {
                map1.put(ch, word);
            }
            
            // Check word -> char mapping
            if (map2.containsKey(word)) {
                if (map2.get(word) != ch) {
                    return false;
                }
            } else {
                map2.put(word, ch);
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter pattern:");
        String pattern = sc.nextLine();
        
        System.out.println("Enter string:");
        String s = sc.nextLine();
        
        boolean result = wordPattern(pattern, s);
        
        System.out.println("Output: " + result);
        
        sc.close();
    }
}