import java.util.*;

public class HIndexProgram {

    static int hIndex(int[] citations) {
        Arrays.sort(citations);

        int h = 0;

        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > h)
                h++;
            else
                break;
        }

        return h;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};

        System.out.println(hIndex(citations));
    }
}