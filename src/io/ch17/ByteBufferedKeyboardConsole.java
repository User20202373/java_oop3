package io.ch17;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class ByteBufferedKeyboardConsole {

    public static void main(String[] args) {

        //System.in
        //System.out


        try (BufferedInputStream bis = new BufferedInputStream(System.in);
             BufferedOutputStream bos = new BufferedOutputStream(System.out)) {

            byte[] buffer = new byte[1024]; // 한 번에 1024바이트씩 읽을 버퍼
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer,0,bytesRead); // 읽은 만큼만 씀
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }//end of main
}
