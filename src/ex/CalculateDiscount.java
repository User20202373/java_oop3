package ex;

import java.util.Scanner;

public class CalculateDiscount {
    // 상수로 선언하면 프로그램 실행 중 값이 변하지 않게 사용 가능
    //고정된 값을 한 곳에서 관리하여 유지보수성을 높임
    static final double RATE = 15.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int price = 0;

        while (true) {
            System.out.println("=== 쇼핑몰 할인된 최종 금액 계산기 ===");
            System.out.println("제품의 고정 할인율은 " + RATE + "% 입니다");
            System.out.println("0 입력 시 종료됩니다");
            System.out.print("제품의 원가를 입력해 주세요: ");
            price = sc.nextInt();

            if (price == 0) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            } else if (price < 0) {
                System.out.println(" 제품의 가격은 음수가 들어갈 수 없습니다.");
            }

            double discount = calculateDiscount(price, RATE); //할인율
            double finalPrice = price - discount; // 총결제 금액

            System.out.println("할인 금액: " + discount+ "원");
            System.out.println("최종 결제 금액 : " + finalPrice + "원 입니다.");
        }
        sc.close();
    }

    public static double calculateDiscount(int price, double rate) {
        return price * (rate / 100.0);
    }
}