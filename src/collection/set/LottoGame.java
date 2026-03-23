package collection.set;

import java.util.*;

public class LottoGame {
    public static void main(String[] args) {

        Set<Integer> lotto = new HashSet<>();
        Random random = new Random();

        //6개가 될 때까지 계속 추가
        while (lotto.size() < 6) {
            int number = random.nextInt(45) + 1; //1~45
            lotto.add(number);

        }
        System.out.println("이번 주 로또 번호 : " + lotto);
        System.out.println("총 " + lotto.size() + " 개");

        //로또 번호 오름차순 정렬
        List<Integer> list = new ArrayList<>(lotto);
        Collections.sort(list);
        System.out.println("로또 오름차순 정렬 : " + list);
    }
}
