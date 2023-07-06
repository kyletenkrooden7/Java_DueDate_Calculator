// Author: Kyle Tenkrooden
package Calculator;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public static DateTime parse(String dateTimeString, String format) {
        String[] dateTimeParts = dateTimeString.split(" ");
        String dateString = dateTimeParts[0];
        String timeString = dateTimeParts[1];

        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        return new DateTime(year, month, day, hour, minute);
    }

    public String format(String format) {
        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }

    public DateTime plusDays(int days) {
        final int DayOfWeek_Saturday = 6;
        final int DayOfWeek_Sunday = 7;

        for (int i = 0; i < days; i++) {
            // Skip weekends (Saturday and Sunday)

            if (getDayOfWeek() == DayOfWeek_Saturday) {
                day += 2;
            } else if (getDayOfWeek() == DayOfWeek_Sunday) {
                day++;
            } else {
                day++;
            }
            if (day > getDaysInMonth(year, month)) {
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }

        return this;
    }

    public DateTime plusHours(int hours) {
        hour += hours;

        while (hour >= 24) {
            hour -= 24;
            plusDays(1);
        }

        return this;
    }

    public DateTime withHour(int hour) {
        this.hour = hour;
        return new DateTime(year, month, day, hour, minute);
    }

    public DateTime withMinute(int minute) {
        this.minute = minute;
        return this;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDayOfWeek() {
        // Zeller's Congruence Algorithm

        int y = year;
        int m = month;
        if (m < 3) {
            m += 12;
            y--;
        }
        int dayOfWeek = (day + 13 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        return dayOfWeek;
    }

    private static int getDaysInMonth(int year, int month) {
        int[] daysInMonth = { 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return daysInMonth[month - 1];
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}
