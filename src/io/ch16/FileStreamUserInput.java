package io.ch16;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FileStreamUserInput {

    public static void main(String[] args) {
        writeUserInputToFile("user_input.txt");
    }

    public static void writeUserInputToFile(String fileName) {
        /**
         * 키보드 입력 -> InputStreamReader(System.in) (바이트 -> 문자 변환)
         * 파일에 쓰기 -> FileWriter(fileName)         (문자 기반 파일 출력)
         */

        try (InputStreamReader reader = new InputStreamReader(System.in);
             FileWriter writer = new FileWriter(fileName, true)) {

            System.out.println("텍스트를 입력하세요(종료 :" + " Ctrl + D)");
            //1. 사용자가 입력한 값을 받자 - 키보드에서
            int charCode;
            while ((charCode = reader.read()) != -1) {
                //[]<--임시 메모리 공간 버퍼
                writer.write(charCode);
                //문자 하나 받을 때 마다 즉시 파일에 저장
            }
            System.out.println(fileName + "에 텍스트를 모두 작성함");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //직접 코드 작성해보기
    //파일에서 텍스트를 읽는 메서드를 직접 구형해 보세요
    public static void readFromFile(String fileName) {
        ///...파일에 내용을 문자 기반으로 읽어서 콘솔창에 출력

        try (FileReader fileReader = new FileReader(fileName)) {
            int data;

            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
