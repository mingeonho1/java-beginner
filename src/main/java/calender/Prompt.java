package calender;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prompt {

    public void runPrompt() {
        Calender calender = new Calender();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int year = promptForYear(sc, getPromptMessage("년도를 입력하세요 : 1990 ~ 2024"));
            if (year == -1) break;

            int month = promptForMonth(sc, getPromptMessage("월을 입력하세요 : 1 ~ 12"));
            if (month == -1) break;

            int maxDays = calender.getMaxDayOf(year, month);

            calender.printCalenderOf(year, month, maxDays);
        }
        sc.close();
    }

    private int promptForYear(Scanner sc, String message) {
        return promptForInt(sc, message);
    }

    private int promptForMonth(Scanner sc, String message) {
        while (true) {
            int month = promptForInt(sc, message);
            if (month == -1 || isValidMonth(month)) {
                return month;
            } else {
                System.out.println("월은 1부터 12까지의 숫자로 입력해주세요.");
            }
        }
    }

    private String getPromptMessage(String message) {
        return message + "\n" + "나기기 : -1\n";
    }

    private int promptForInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.println(message);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력하세요.");
                sc.nextLine();
            }
        }
    }

    private boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }
}
