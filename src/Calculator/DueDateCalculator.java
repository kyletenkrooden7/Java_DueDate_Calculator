package Calculator;

public class DueDateCalculator {
    private static final int WORK_START_HOUR = 9;
    private static final int WORK_END_HOUR = 17;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static String calculateDueDate(String submitDateTime, int turnaroundTime) {
        DateTime submitDate = DateTime.parse(submitDateTime, DATE_TIME_FORMAT);
        DateTime dueDate = calculateDueDate(submitDate, turnaroundTime);
        return dueDate.format(DATE_TIME_FORMAT);
    }

    private static DateTime calculateDueDate(DateTime submitDate, int turnaroundTime) {
        DateTime dueDate = adjustToStartOfWorkDay(submitDate);
        while (turnaroundTime > 0) {
            if (isWorkingDay(dueDate)) {
                int remainingHoursInDay = WORK_END_HOUR - dueDate.getHour();
                if (remainingHoursInDay > turnaroundTime) {
                    dueDate = dueDate.plusHours(turnaroundTime);
                    turnaroundTime = 0;
                } else {
                    turnaroundTime -= remainingHoursInDay;
                    dueDate = dueDate.plusDays(1);
                    dueDate = dueDate.withHour(WORK_START_HOUR);
                }
            } else {
                dueDate = dueDate.plusDays(1);
            }
        }
        return dueDate;
    }

    private static DateTime adjustToStartOfWorkDay(DateTime date) {
        if (date.getHour() < WORK_START_HOUR) {
            date = date.withHour(WORK_START_HOUR).withMinute(0);
        } else if (date.getHour() >= WORK_END_HOUR || (date.getHour() == WORK_END_HOUR && date.getMinute() > 0)) {
            date = date.plusDays(1);
            date = date.withHour(WORK_START_HOUR).withMinute(0);
        }
        return date;
    }

    private static boolean isWorkingDay(DateTime date) {
        int dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != 6 && dayOfWeek != 7;
    }
}
