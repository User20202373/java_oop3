package io.ch15_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 학생들의 시험 점수를 파일에 저장하고,
 * 파일에서 읽어서 총점과 평균을 계산합니다.
 * 점수는 아래 형식으로 scores.txt 에 저장합니다.
 * <p>
 * scores.txt 형식:
 * 80 90 75 95 60
 * (점수를 공백으로 구분해서 한 줄에 저장)
 *
 */
public class TestScoresSave {
    public static void main(String[] args) {
        // System.out.println("점프 " + Integer.valueOf(' ')); 점프는 32
        Scanner sc = new Scanner(System.in);
        System.out.print("학생의 점수를 입력하세요: ");
        String score = sc.nextLine();

        System.out.println("입력된 점수 : " + score);

        // 점수를 파일에 저장하기
        saveScore(score);

        // 점수를 파일에서 출력하기
        loadScore();


        sc.close(); // 누수 방지
    } // end of main

    private static void loadScore() {
        int totalScore = 0;
        int count = 0; //학생 수
        System.out.println("=== 학생들의 점수 ===");
        try {
            FileInputStream fis = new FileInputStream("scores.txt");
            int data;
            int saveData = 0;
            int i = 1;
            // 여기서 데이터는 문자로 저장됨
            while ((data = fis.read()) != -1) {
                int value = Character.getNumericValue(data); // 읽은 캐릭터를 넘버로 변환
                // 데이터가 다음 숫자면 기존 숫자를 * 10 하고 현재 숫자를 더하면 됨
                if (value != -1) {
                    // System.out.print(value);
                    saveData = saveData * 10 + value;

                } else {
                    System.out.println("학생" + i + "점수 : " + saveData);

                    // 다음으로 넘어가기면 해당 값 저장
                    // System.out.println("");
                    totalScore += saveData;
                    count++;
                    i++;
                    // 초기화
                    saveData = 0;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== 학생들의 점수 총합 ===");
        System.out.println(totalScore);
        System.out.println("=== 학생들의 점수 평균 ===");
        if (count > 0) {
            double avg = (double) totalScore / count;
            System.out.println(avg);
        } else {
            System.out.println("저장된 점수가 없습니다.");
        }
    }

    private static void saveScore(String score) {
        try {
            FileOutputStream fos = new FileOutputStream("scores.txt", true);
            fos.write(score.getBytes());
            fos.write(" ".getBytes()); // 줄바꿈
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}