package calender;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("두 수를 입력해 주세요!");
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.printf("두 수의 합은 %d 입니다.", a + b);
        sc.close();
    }
}
