package collection;

import java.util.*;

//역할 - 데이터를 보관하고 관리하는 클래스
public class MemberRepository {

    private List<Member> memberList = new ArrayList<>(); //전체 목록
    private Map<Integer, Member> memberMap = new HashMap<>(); // ID -> 회원정보
    private Set<String> emailSet = new HashSet<>(); // 이메일 중복 체크
    //회원 가입하면 ID를 자동으로 1씩 증가하는 방법으로 회원에게 할당할 예정
    private int nextId = 1;

    //회원가입
    public boolean save(String name, String email, int age) {
        //이메일 중복 확인
        if (emailSet.contains(email)) {
            System.out.println("[오류] 이미 사용중인 이메일 입니다" + email);
            return false;
        }

        Member member = new Member(nextId, name, email, age);
        memberList.add(member);
        memberMap.put(nextId, member);
        emailSet.add(email);
        nextId++;

        System.out.println("[완료] 회원가입 : " + member.getName());
        return true;
    }

    //ID로 회원 조회 -- 1 (입력) --> 반환 (Member)
    public Member findById(int id) {
        return memberMap.get(id);

    }


    //전체 회원 목록 조회 -- 받아야 할 정보? 모든 Member 정보 리턴 --? List 사용

    public List<Member> findAll() {
        return memberList;

    }

    //이름으로 회원 조회
    public List<Member> findByName(String name) {
        List<Member> result = new ArrayList<>();
        for (Member member : memberList) {
            if (member.getName().equalsIgnoreCase(name)) {
                result.add(member);

            }
        }
        return result;
    }

    //도전과제 나이 범위로 검색
    public List<Member> findByAgeRange(int max, int min) {
        List<Member> findAge = new ArrayList<>();
        // 전체 순회
        for (Member member : memberList){
            if ( member.getAge() >= min && member.getAge() <= max){
                findAge.add(member);
            }
        }
        return findAge;
        // 전체 순회하면서 만약 조건이 맞다면 즉 max 보다 작거나 같고 min보다 크거나 같다면 true
        //잠시 어딘가에 저장
        //전체 순회 종료
        //결과값 반환
    }

    // 이메일로 회원 찾기
    public List<Member> findByEmail(String email) {
        List<Member> result = new ArrayList<>();
        for (Member member : memberList) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                result.add(member);
            }
        }
        return result;

    }

    //회원 정보 수정
    //id 기준으로 회원 정보 조회
    //2. 새로운 이름 정보 필요
    //3. 새로운 나이 정보 필요
    //4. 이메일은 중복 처리 때문에 수정 불가하게 막을 예정.
    public boolean update(int id, String newName, int newAge) {
        //수정을 하려면 반드시 먼저 조회를 해야한다
        Member member = memberMap.get(id);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다 ID : " + id);
            return false;
        }
        member.setName(newName);
        member.setAge(newAge);
        System.out.println("[완료] tnwjdehla: " + member);
        return true;
    }


    //회원 ID 번호로 삭제
    public boolean delete(int id) {
        //먼저 조회부터 하고 삭제하기
        Member member = memberMap.get(id);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다 ID : " + id);
            return false;
        }

        //list, map , set 에서 다 삭제 해줘야 하니 동기화 처리 중요
        memberList.remove(id - 1);// 인덱스 번호로 삭제하기 때문에 보여지는 값에서 -1 (오류 발생 지점)
        memberMap.remove(id);
        emailSet.remove(member.getEmail());
        System.out.println("삭제 됨 : " + member.getEmail());
        return true;

    }

    //전체 회원 수
    public int count() {
        return memberList.size();
    }

}
