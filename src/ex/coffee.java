package ex;

import java.util.Scanner;

public class coffee {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int coffee = 2500;
        int choice = 0;
        while (true) {
            System.out.println("무인 주문 키오스크");
            System.out.println("아메리카노 가격 : " + coffee);
            System.out.print("수량 선택 : ");
            choice = sc.nextInt();
            if (choice <= 0) {
                System.out.println("1잔 이상 주문해야합니다");
            } else if (choice >= 1) {
                break;
            } else {
                System.out.println("숫자를 입력해 주세요");
            }
        }
        int price = choice * coffee;
        System.out.println("=== 주문 내역 ===");
        System.out.println(" 총 주문 수량 : " + choice);
        System.out.println(" 총 결제 금액 : " + price);

        if (choice >= 3) {
            System.out.println("특별 스탬프");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(" * ");
                }
                System.out.println();
            }
            System.out.println("---------------");
        }
        sc.close();
    }
}
