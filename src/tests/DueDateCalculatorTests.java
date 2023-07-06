package tests;

import Calculator.DueDateCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DueDateCalculatorTests {

    @Test
    void testCalculateDueDate_SameDayWithinWorkingHours() {
        String submitDateTime = "2023-07-05 09:00"; // Tuesday, 9:00 AM
        int turnaroundTime = 2; // 2 hours

        String dueDateTime = DueDateCalculator.calculateDueDate(submitDateTime, turnaroundTime);
        String expectedDateTime = "2023-07-05 11:00"; // Tuesday, 11:00 AM

        assertEquals(expectedDateTime, dueDateTime);
    }

    @Test
    void testCalculateDueDate_SameDayOutsideWorkingHours() {
        String submitDateTime = "2023-07-05 17:00"; // Tuesday, 5:00 PM
        int turnaroundTime = 2; // 2 hours

        String dueDateTime = DueDateCalculator.calculateDueDate(submitDateTime, turnaroundTime);
        String expectedDateTime = "2023-07-06 11:00"; // Wednesday, 11:00 AM

        assertEquals(expectedDateTime, dueDateTime);
    }

    @Test
    void testCalculateDueDate_NextDay() {
        String submitDateTime = "2023-07-05 14:12"; // Tuesday, 2:12 PM
        int turnaroundTime = 8; // 8 hours

        String dueDateTime = DueDateCalculator.calculateDueDate(submitDateTime, turnaroundTime);
        String expectedDateTime = "2023-07-06 14:12"; // Thursday, 2:12 PM

        assertEquals(expectedDateTime, dueDateTime);
    }

    @Test
    void testCalculateDueDate_Weekend() {
        String submitDateTime = "2023-07-07 09:00"; // Friday, 9:00 AM
        int turnaroundTime = 8; // 8 hours

        String dueDateTime = DueDateCalculator.calculateDueDate(submitDateTime, turnaroundTime);
        String expectedDateTime = "2023-07-10 09:00"; // Monday, 09:00 AM

        assertEquals(expectedDateTime, dueDateTime);
    }
}
