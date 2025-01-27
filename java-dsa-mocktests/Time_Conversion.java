
public class Time_Conversion{
    public static String timeConversion(String s) {
        // Ensure input is at least 10 characters
        if (s.length() < 10) {
            throw new IllegalArgumentException("Invalid input format");
        }

        // Extract the period (AM/PM)
        String period = s.substring(s.length() - 2).toUpperCase(); // Normalize to uppercase
        System.out.println("Extracted period: " + period); // Debug statement

        // Extract hour, minute, second
        String hour = s.substring(0, 2); // First 2 characters (hour)
        String minute = s.substring(3, 5); // Characters 3-4 (minute)
        String second = s.substring(6, 8); // Characters 6-7 (second)

        // Convert hour to integer
        int hourInt = Integer.parseInt(hour);

        // Convert time based on AM/PM
        if (period.equals("AM")) {
            if (hourInt == 12) {
                hourInt = 0; // Convert 12 AM to 00
            }
        } else if (period.equals("PM")) {
            if (hourInt != 12) {
                hourInt += 12; // Add 12 for PM hours (except 12 PM)
            }
        }

        // Format the hour as two digits and combine with minute and second
        String militaryHour = String.format("%02d", hourInt);
        return militaryHour + ":" + minute + ":" + second;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(timeConversion("07:05:45PM")); // Expected: 19:05:45
        System.out.println(timeConversion("12:00:00AM")); // Expected: 00:00:00
        System.out.println(timeConversion("12:00:00PM")); // Expected: 12:00:00
        System.out.println(timeConversion("01:00:00AM")); // Expected: 01:00:00
    }
}