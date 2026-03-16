package io._my;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class TestScore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 시험 점수 저장소 ===");
        System.out.println("1. 시험 점수 입력");
        System.out.println("2. 저장된 시험 점수 보기");
        System.out.println("3. 저장소 종료");
        System.out.print("선택 : ");
        boolean b1 = true;
        String choice = sc.nextLine();
        while (b1) {
            if (choice.equals("1")) {
                saveScore(sc);
            } else if (choice.equals("2")) {
                printScore();
            } else if (choice.equals("3")) {
                b1 = false;
            } else {
                break;
            }

        }

    }

    private static void printScore() {
        System.out.println("저장된 시험 점수");
        try (FileInputStream fin = new FileInputStream("test_Score.txt")) {
            int scoreBox;
            while ((scoreBox = fin.read()) != -1) {
                System.out.println((char) scoreBox + " ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveScore(Scanner sc) {
        System.out.print("시험 점수 입력 :");
        int score = sc.nextInt();

        try (FileOutputStream fos = new FileOutputStream("test_Score.txt", true)) {
            fos.write(score);
            System.out.println("점수 저장 완료");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
