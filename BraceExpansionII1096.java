import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression);
        return new ArrayList<>(result);
    }

    private Set<String> solve(String s) {
        Set<String> result = new TreeSet<>();

        int balance = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '{') {
                balance++;
            } else if (ch == '}') {
                balance--;
            } else if (ch == ',' && balance == 0) {
                result.addAll(solve(s.substring(start, i)));
                start = i + 1;
            }
        }

        if (start != 0) {
            result.addAll(solve(s.substring(start)));
            return result;
        }

        Set<String> current = new TreeSet<>();
        current.add("");

        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '{') {
                int balance2 = 1;
                int j = i + 1;

                while (balance2 > 0) {
                    if (s.charAt(j) == '{') {
                        balance2++;
                    } else if (s.charAt(j) == '}') {
                        balance2--;
                    }
                    j++;
                }

                Set<String> part = solve(s.substring(i + 1, j - 1));
                current = multiply(current, part);
                i = j;
            } else {
                Set<String> part = new TreeSet<>();
                part.add(String.valueOf(s.charAt(i)));
                current = multiply(current, part);
                i++;
            }
        }

        return current;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
