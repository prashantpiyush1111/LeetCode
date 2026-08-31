import java.util.*;

public class NodesBetweenCriticalPoints {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int min = Integer.MAX_VALUE;

        int pos = 1;
        ListNode a = head;
        ListNode b = head.next;
        ListNode c = b.next;

        while (c != null) {
            boolean critical = (b.val > a.val && b.val > c.val) ||
                               (b.val < a.val && b.val < c.val);

            if (critical) {
                if (first == -1) {
                    first = pos;
                }

                if (prev != -1) {
                    min = Math.min(min, pos - prev);
                }

                prev = pos;
            }

            a = b;
            b = c;
            c = c.next;
            pos++;
        }

        if (first == -1 || prev == first) {
            return new int[]{-1, -1};
        }

        return new int[]{min, prev - first};
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
