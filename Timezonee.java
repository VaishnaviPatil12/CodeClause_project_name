import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Timezonee {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the local date and time from user input
        System.out.print("Enter the local date and time (yyyy-MM-dd HH:mm:ss): ");
        String localDateTimeString = scanner.nextLine();
        DateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = null;
        try {
            localDate = localDateFormat.parse(localDateTimeString);
        } catch (Exception e) {
            System.out.println("Invalid date/time format entered");
            return;
        }

        // Print the local date and time
        System.out.println("Local date and time: " + localDateFormat.format(localDate));

        // Get the user input for the country
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();

        // Get the time zone of the country
        TimeZone countryTimeZone = getTimeZone(country);
        if (countryTimeZone == null) {
            System.out.println("Invalid country entered");
            return;
        }
        System.out.println("Time zone of " + country + ": " + countryTimeZone.getID());

        // Convert the local date and time to the country's time zone
        DateFormat countryDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        countryDateFormat.setTimeZone(countryTimeZone);
        System.out.println("Date and time in " + country + ": " + countryDateFormat.format(localDate));
    }

    // Method to get the time zone of a country
    public static TimeZone getTimeZone(String country) {
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String id : availableIDs) {
            TimeZone timeZone = TimeZone.getTimeZone(id);
            if (timeZone.getDisplayName().toLowerCase().contains(country.toLowerCase())) {
                return timeZone;
            }
        }
        return null;
    }
}