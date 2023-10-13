package calender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Schedule {

    private static final HashMap<LocalDate, ArrayList<String>> schedules = new HashMap<>();

    public void createSchedule(String strDate, String scheduleContent) {
        try {
            LocalDate date = LocalDate.parse(strDate);
            if (schedules.get(date) == null) {
                ArrayList<String> newSchedule = new ArrayList<>();
                newSchedule.add(scheduleContent);
                schedules.put(date, newSchedule);
                System.out.println("일정 등록을 성공했습니다.");
            } else {
                schedules.get(date).add(scheduleContent);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("""
                올바른 일정을 입력해주세요
                EX: 2023-09-10, 1990-10-09
                """);
        }
    }

    public Optional<ArrayList<String>> findSchedule(String strDate) {
        try {
            LocalDate date = LocalDate.parse(strDate);

            if (schedules.get(date) == null) {
                System.out.printf("%s에는 일정이 없습니다 !\n", date);
                return Optional.empty();
            } else {
                return Optional.ofNullable(schedules.get(date));
            }
        } catch (Exception e) {
            System.out.println("""
                올바른 일정을 입력해주세요
                EX: 2023-09-10, 1990-10-09
                """);
            return Optional.empty();
        }
    }

    public boolean existsSchedules(int year, int month, int day) {
        return (schedules.get(LocalDate.of(year, month, day)) != null);
    }
}
