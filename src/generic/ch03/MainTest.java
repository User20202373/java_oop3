package generic.ch03;

import generic.ch02.GenericPrinter;
import generic.ch02.Water;

public class MainTest {

    public static void main(String[] args) {

        Water water1 = new Water();
        Plastic plastic1 = new Plastic();
        Powder powder1 = new Powder();

        //GenericExtendsPrinter <-- 제네릭 클래스로 설계 되어 있어서 <> 를 사용할 수 있다.

        //GenericExtendsPrinter<> printer = new GenericExtendsPrinter();
        //오류 발생 = Water 타입은 Material의 자식이 아니기 때문에 사용할 수 없음

        GenericExtendsPrinter<Plastic> printer = new GenericExtendsPrinter<>();
        printer.setMaterial(plastic1); //주소 값

        //System.out.println(printer.getMaterial().showInfo());<-- showInfo()가 void반환하기 때문에 오류
        System.out.println(printer.getMaterial());
        printer.getMaterial().showInfo();

    }
}
