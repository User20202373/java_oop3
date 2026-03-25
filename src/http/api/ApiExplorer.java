package http.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        //요청할 URL 만들기
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "vrBoYA%2BMQvPHugV9ZdXZHMhQ5qKM2cGSNOmpqtYgizv2aU6tX7C2Mhpug5Qf%2F7a2X6F%2FL2w2jm%2FCbJ5%2Fyzifug%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2025", "UTF-8")); /*측정 연도*/
        urlBuilder.append("&" + URLEncoder.encode("itemCode", "UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
       //URL을 HTTP로 연결
        URL url = new URL(urlBuilder.toString()); //문자열 URL을 실제URL객체로 바꿈
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //GET방식으로 요청
        conn.setRequestMethod("GET");

        //요청 헤더 설정하는 코드, JSON관련 내용을 다루고 있다는 정도
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("요청 상태 : " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));//정상출력일 때 서버 응답 가져옴
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));//오류났을 때 오류 코드 가져옴
        }
        StringBuilder sb = new StringBuilder(); // sb는 서버가 준 응답 내용을 누적해서 저장할 변수
        String line;//한 줄씩 읽을 때 임시로담는 변수
        while ((line = rd.readLine()) != null) { //rd.readLine()로 한 줄씩 읽고 더이상 읽을 줄이 없으면 종료
            sb.append(line);//읽은 줄을 sb에 계속 이어붙여 전체 응답 문자열 생성
        }
        rd.close();//입력 스트림 닫기
        conn.disconnect();//서버 연결 종료
        System.out.println(sb.toString());//결과 출력
    }
}