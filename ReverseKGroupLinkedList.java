class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseKGroupLinkedList {

    public ListNode reverseKGroup(ListNode head, int k) {

        if (k == 1) {
            return head;
        }

        int count = 0;
        ListNode nodePrev, nodeNext, temp;
        nodePrev = nodeNext = temp = null;

        nodePrev = new ListNode(-1);
        nodePrev.next = head;

        temp = head;

        while (temp != null) {
            count++;

            if (count == k) {
                nodeNext = temp.next;
                temp.next = null;

                ListNode[] reversed = reverse(nodePrev.next);

                if (nodePrev.next == head) {
                    head = reversed[0];
                }

                nodePrev.next = reversed[0];
                reversed[1].next = nodeNext;

                nodePrev = reversed[1];
                temp = nodeNext;
                count = 0;

            } else {
                temp = temp.next;
            }
        }

        return head;
    }

    private static ListNode[] reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode[] res = new ListNode[2];

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;

            if (prev == null) {
                res[1] = curr;
            }

            prev = curr;
            curr = temp;
        }

        res[0] = prev;
        return res;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReverseKGroupLinkedList obj = new ReverseKGroupLinkedList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        System.out.println("Original List:");
        printList(head);

        head = obj.reverseKGroup(head, k);

        System.out.println("Reversed in K Group:");
        printList(head);
    }
}