package calender;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Prompt {

    public void runPrompt() {
        Calender calender = new Calender();
        Schedule schedule = new Schedule();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int menu = promptForMenu(sc);

            switch (menu) {
                case 1 -> {
                    String createScheduleDate = promptForString(sc, "등록할 일정을 입력하세요. (YYYY-MM-DD EX: 2023-10-01)");
                    String createScheduleContent = promptForString(sc, "등록할 일정을 내용을 입력하세요.");
                    schedule.createSchedule(createScheduleDate, createScheduleContent);
                }
                case 2 -> {
                    String scheduleDate = promptForString(sc, "찾는 일정을 입력하세요. (YYYY-MM-DD EX: 2023-10-01)");
                    Optional<ArrayList<String>> schedules = schedule.findSchedule(scheduleDate);
                    schedules.ifPresent(this::printScheduleContents);
                }
                case 3 -> {
                    int year = promptForYear(sc);
                    int month = promptForMonth(sc);
                    int maxDays = calender.getMaxDayOf(year, month);
                    calender.printCalenderOf(year, month, maxDays);
                }
                case 9 -> printHelp();
                case -1 -> {
                    sc.close();
                    return;
                }
            }
        }
    }

    private int promptForYear(Scanner sc) {
        return promptForInt(sc, "년도를 입력하세요. (1990 ~ 2024)");
    }

    private int promptForMonth(Scanner sc) {
        while (true) {
            int month = promptForInt(sc, "월을 입력하세요. (1 ~ 12)");
            if (month == -1 || isValidMonth(month)) {
                return month;
            } else {
                System.out.println("월은 1부터 12까지의 숫자로 입력해주세요.");
            }
        }
    }

    private int promptForMenu(Scanner sc) {
        System.out.println("""
            +------------------------+
            | 1. 일정 등록
            | 2. 일정 검색
            | 3. 달력 보기
            | 9. 도움말
            | -1. 종료
            +------------------------+
            """);
        return promptForInt(sc, "");
    }

    private void printHelp() {
        System.out.println("""
            +-----------------------------------------------------------------------+
            | 1. 일정 등록은 YYYY-MM-DD 형식으로 일정일을 입력하고, 일정 내용을 입력하면 된다.
            | 2. 일정 검색은 등록했던 일정일을 YYYY-MM-DD 형식으로 입력하면 일정 내용이 나온다.
            | 3. 달력 보기는 년도와 월을 입력하면 달력을 볼 수 있다.
            | -1. 종료는 프로그램을 종료한다.
            +-----------------------------------------------------------------------+
            """);
    }

    private void printScheduleContents(ArrayList<String> scheduleContents) {
        System.out.println("-------- 일정 내용 --------");
        for (String sch : scheduleContents) {
            System.out.println(sch);
        }
        System.out.println("-------------------------");
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

    private String promptForString(Scanner sc, String message) {
        System.out.println(message);
        return sc.next();
    }

    private boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }
}
