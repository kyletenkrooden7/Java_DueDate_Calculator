package Calculator;

public class DueDateCalculatorDriver {
    public static void main(String[] args) {
        // Example 1
        String submitDateTime1 = "2023-07-21 16:12";
        int turnaroundTimeHr1 = 8;

        String dueDateTime1 = DueDateCalculator.calculateDueDate(submitDateTime1, turnaroundTimeHr1);
        String expectedDateTime1 = "2023-07-24 16:12";

        System.out.println("Example 1:");
        System.out.println("Submit Date/Time: " + submitDateTime1);
        System.out.println("Turnaround Time: " + turnaroundTimeHr1 + " hours");
        System.out.println("Due Date/Time: " + dueDateTime1);
        System.out.println("Expected Due Date/Time: " + expectedDateTime1);
        System.out.println();

        // Example 2
        String submitDateTime2 = "2023-07-07 12:30";
        int turnaroundTimeHr2 = 5;

        String dueDateTime2 = DueDateCalculator.calculateDueDate(submitDateTime2, turnaroundTimeHr2);
        String expectedDateTime2 = "2023-07-10 09:30";

        System.out.println("Example 2:");
        System.out.println("Submit Date/Time: " + submitDateTime2);
        System.out.println("Turnaround Time: " + turnaroundTimeHr2 + " hours");
        System.out.println("Due Date/Time: " + dueDateTime2);
        System.out.println("Expected Due Date/Time: " + expectedDateTime2);
        System.out.println();
    }
}
