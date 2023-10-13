package calender;

public class Calender {

    private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int getMaxDayOf(int year, int month) {
        if (isLeapYear(year)) {
            return LEAP_MAX_DAYS[month - 1];
        } else {
            return MAX_DAYS[month - 1];
        }
    }

    void printCalenderOf(int year, int month, int maxDays) {
        System.out.printf("      <<%4d년%d월>>\n", year, month);
        System.out.println("일  월  화  수  목  금  토");
        System.out.println("-------------------------");
        for (int i = 1; i <= maxDays; i++) {
            System.out.printf("%-3d ", i);
            if (i % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("-------------------------\n");
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
}

