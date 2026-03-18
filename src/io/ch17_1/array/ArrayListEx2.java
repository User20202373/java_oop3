package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx2 {

    public static void main(String[] args) {
        //정수, 실수 ,불리언, 사용자 정의 객체를 담을 수 있는 ArrayList 각각 만들어서 사용해 보기
        //사용방법 스스로 익혀보기
        ArrayList<String> list1 = new ArrayList<>();

        list1.add("홍길동전");
        list1.add("ToDo자바");
        list1.add("시집");
        System.out.println(list1.getFirst());
        System.out.println(list1.getLast());

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(10);
        System.out.println(list2.get(0));

        ArrayList<Double> list3 = new ArrayList<>();
        list3.add(3.14);
        System.out.println(list3.get(0));

        ArrayList<Boolean> list4 = new ArrayList<>();
        list4.add(true);
        System.out.println(list4.get(0));


    }

    class Book {
        String title;

    }
}
