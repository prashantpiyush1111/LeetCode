
public class DayOfYearCalculator { 

    // Calculates the day number of the year for a given date string (YYYY-MM-DD)
    public int dayOfYear(String date) { 
        String[] parts = date.split("-"); 
        int year = Integer.parseInt(parts[0]); 
        int month = Integer.parseInt(parts[1]); 
        int day = Integer.parseInt(parts[2]); 
        
        // Days accumulated before the start of each month
        int daysbeforemonth[] = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 }; 
        int totaldays = daysbeforemonth[month - 1] + day; 
        
        // Check for leap year conditions
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) { 
            if (month > 2) {
                totaldays += 1; 
            }
        } 
        
        return totaldays; 
    } 

    // Main function to run and test the code
    public static void main(String[] args) {
        DayOfYearCalculator calculator = new DayOfYearCalculator();
        
        // Test sample date (March 1st, 2024 is the 61st day of a leap year)
        String testDate = "2024-03-01";
        int result = calculator.dayOfYear(testDate);
        
        System.out.println("The date " + testDate + " is day number: " + result);
    }
}
