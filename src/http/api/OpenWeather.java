package http.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeather {

    public static void main(String[] args) {
        String apiKey = "f9ee3f4e5fd02643fc39cb4a264ba57e"; // 발급받은 API 키 입력
        String city = "Seoul";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + apiKey + "&units=metric&lang=kr";

        try {
            // 1. URL 객체 생성 및 연결 설정
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // 연결 타임아웃 5초
            conn.setReadTimeout(5000);    // 읽기 타임아웃 5초
            System.out.println(urlString.toString());

            // 2. 응답 코드 확인 (200 OK 인지 체크)
            int responseCode = conn.getResponseCode();

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                //통신 성공
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                //통신 성공은 했으나 응답 잘못 ,실패
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            //System.out.println(sb.toString());
            String jsonString = sb.toString();
            //System.out.println("JSON 응답 : " + sb);

            //도전 문제 1 - 파싱 처리 직접
            //출력 값
            //상태: 맑음
            //기온 : 18,5
            //습도 42%
            //풍속2.1m/s
            Gson gson = new Gson();
            OpenWeatherDTO weatherDTO = gson.fromJson(jsonString.toString(), OpenWeatherDTO.class);
            System.out.println("도시 이름 : " + weatherDTO.getName());
            System.out.println("상태 : " + weatherDTO.getWeather().get(0).getDescription());
            System.out.println("기온 : " + weatherDTO.getMain().getTemp());
            System.out.println("습도 : " + weatherDTO.getMain().getHumidity());
            System.out.println("풍속 : " + weatherDTO.getWind().getSpeed());

            //ㅏ---간단한 날씨 알림 로직 --
            //만약 25도 보다 크다면 오늘 덥다
            //그 이하라면 날씨가 쌀쌀합니다
            //오늘은 날씨가 따뜻하네요.산책하기

            if (weatherDTO.getMain().getTemp() > 25) {
                System.out.println("오늘 덥다");
            } else if (weatherDTO.getMain().getTemp() <= 25) {
                System.out.println("날씨가 쌀쌀합니다");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}