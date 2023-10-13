package calender;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calender {

    private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Calender calender = new Calender();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int month = calender.promptForMonth(sc,
                """
                    월을 입력하세요 : 1 ~ 12
                    나기기 : -1
                    """);
            if (month == -1) {
                System.out.println("종료됨.");
                break;
            }

            int maxDays = calender.getMaxDayOf(month);

            calender.printCalenderOf(2023, month, maxDays);
        }
        sc.close();
    }

    private int promptForMonth(Scanner sc, String message) {
        while (true) {
            try {
                System.out.println(message);
                int month = sc.nextInt();
                if (month == -1 || isValidMonth(month)) {
                    return month;
                } else {
                    System.out.println("월은 1부터 12까지의 숫자로 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력하세요.");
                sc.nextLine();
            }
        }
    }

    private int getMaxDayOf(int month) {
        return MAX_DAYS[month - 1];
    }

    private void printCalenderOf(int year, int month, int maxDays) {
        System.out.printf("     <<%4d년%d월>>\n", year, month);
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

    private boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }
}

