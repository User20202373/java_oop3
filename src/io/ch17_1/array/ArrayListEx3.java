package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx3 {

    public static void main(String[] args) {
        // 1. 정수를 담은 리스트
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("정수 리스트 : " + intList);

        // 2. 실수를 담은 리스트
        ArrayList<Double> doubleList = new ArrayList<Double>();
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);
        System.out.println(doubleList);

        // 3. 불리언 담을 리스트
        ArrayList<Boolean> booleanList = new ArrayList<>();
        booleanList.add(true);
        booleanList.add(false);

        // 사용자 정의 객체를 담는 리스트
        ArrayList<Book2> book2List = new ArrayList<>();
        book2List.add(new Book2("자바책"));
        book2List.add(new Book2("RDBMS책"));

        // book2List.get(0) ==> 주소값.title
        System.out.println(book2List.get(0).title);
        System.out.println(book2List.get(1).title);

        try {
            System.out.println(book2List.get(2).title);
        } catch (Exception e) {
            e.printStackTrace(); // sout
        }
        System.out.println("프로그램 정상 종료");
    } // end of main
} // end of class

class Book2 {
    String title;

    public Book2(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "[title=" + title + "]";
    }
}
