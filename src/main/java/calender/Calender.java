package calender;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calender {

    private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Calender calender = new Calender();
        Scanner sc = new Scanner(System.in);

        int repeat = calender.readIntOf(sc, "반복 횟수를 입력하세요.");

        for (int i = 0; i < repeat; i++) {
            int month = calender.getValidMonth(sc);

            int maxDays = calender.getMaxDayOf(month);

            calender.printCalenderOf(maxDays);

            System.out.printf("%d월은 %d일까지 있습니다.\n", month, maxDays);
        }
        sc.close();
    }

    private int getValidMonth(Scanner sc) {
        int month = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            month = readIntOf(sc, "월을 입력하세요.");

            if (isValidMonth(month)) {
                isValidInput = true;
            } else {
                System.out.println("올바른 월(1에서 12 사이)을 입력하세요.");
            }
        }

        return month;
    }

    private int getMaxDayOf(int month) {
        return MAX_DAYS[month - 1];
    }

    private void printCalenderOf(int maxDays) {
        System.out.println("일  월  화  수  목  금  토");
        System.out.println("-------------------------");
        for (int i = 1; i <= maxDays; i++) {
            System.out.printf("%-3d ", i);
            if (i % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("-------------------------");
    }

    private boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    private int readIntOf(Scanner sc, String message) {
        int i = 0;
        try {
            System.out.println(message);
            i = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("올바른 숫자를 입력하세요.");
            sc.nextLine();
        }
        return i;
    }

    private void printSampleCalender() {
        System.out.println("일  월  화  수  목  금  토");
        System.out.println("-------------------------");
        System.out.println("8  9  10 11 12 13 14");
        System.out.println("1  2  3  4  5  6  7");
        System.out.println("15 16 17 18 19 20 21");
        System.out.println("15 16 17 18 19 20 21");
        System.out.println("22 23 24 25 26 27 28");
        System.out.println("29 30");
        System.out.println("-------------------------");
    }
}

