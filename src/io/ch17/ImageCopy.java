package io.ch17;

import java.io.*;

public class ImageCopy {

    public static void main(String[] args) {

        String sourceFile = "abc.png";
        String destFile = "abc2.png";
        long startTime = System.currentTimeMillis();

        //abc.png 파일을 읽어서
        try (FileInputStream fis = new FileInputStream(sourceFile);
             //abc2.png 파일을 만들어 보세요(복사)
             FileOutputStream fos = new FileOutputStream(destFile)) {
            int data; //데이터 담을 변수
            //데이터 읽고 쓰기
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("파일 복사 완료");
            System.out.println("소요시간 : " + (endTime - startTime) + "ms");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
