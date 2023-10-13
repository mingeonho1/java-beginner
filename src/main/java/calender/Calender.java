package calender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Calender {

    private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] DAYS_OF_WEEK = {
        DayOfWeek.SUNDAY.name(),
        DayOfWeek.MONDAY.name(),
        DayOfWeek.TUESDAY.name(),
        DayOfWeek.WEDNESDAY.name(),
        DayOfWeek.THURSDAY.name(),
        DayOfWeek.FRIDAY.name(),
        DayOfWeek.SATURDAY.name()
    };
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private final Schedule schedule = new Schedule();

    int getMaxDayOf(int year, int month) {
        if (isLeapYear(year)) {
            return LEAP_MAX_DAYS[month - 1];
        } else {
            return MAX_DAYS[month - 1];
        }
    }

    void printCalenderOf(int year, int month, int maxDays) {
        DayOfWeek startDay = getStartDayOf(year, month);
        int startDayValue = (startDay == DayOfWeek.SUNDAY) ? 1 : startDay.getValue() + 1;

        // 달력 출력 부분
        System.out.printf("      <<%4d년%d월>>\n", year, month);
        System.out.println(getShortenedWeekdayNames());
        System.out.println("--------------------------");
        printInitialSpaces(startDayValue);
        printDays(year, month, startDayValue, maxDays);
        System.out.println("--------------------------\n");
    }

    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else {
            return year % 400 == 0;
        }
    }

    public DayOfWeek getStartDayOf(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        return firstDayOfMonth.getDayOfWeek();
    }

    private String getShortenedWeekdayNames() {
        return Arrays.stream(DAYS_OF_WEEK)
            .map(week -> week.substring(0, 2))
            .collect(Collectors.joining("  "));
    }

    private void printInitialSpaces(int startDayValue) {
        for (int i = 1; i < startDayValue; i++) {
            System.out.print("    ");
        }
    }

    private void printDays(int year, int month, int startDayValue, int maxDays) {
        try {
            int currentDay = startDayValue;
            for (int i = 1; i <= maxDays; i++) {
                if (schedule.existsSchedules(year, month, i)) System.out.print(ANSI_RED);
                System.out.printf("%-3d ", i);
                System.out.print(ANSI_RESET);

                if (currentDay % 7 == 0) {
                    System.out.println();
                }
                currentDay++;
                if (currentDay > 7) currentDay = 1;
            }

            if ((currentDay - 1) % 7 != 0) {
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

