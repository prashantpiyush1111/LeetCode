public class CountDaysSpentTogether {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int aliceStart = convertDate(arriveAlice, monthDays);
        int aliceEnd = convertDate(leaveAlice, monthDays);
        int bobStart = convertDate(arriveBob, monthDays);
        int bobEnd = convertDate(leaveBob, monthDays);

        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        return Math.max(0, end - start + 1);
    }

    private int convertDate(String date, int[] monthDays) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        int totalDays = day;

        for (int i = 0; i < month - 1; i++) {
            totalDays += monthDays[i];
        }

        return totalDays;
    }
}