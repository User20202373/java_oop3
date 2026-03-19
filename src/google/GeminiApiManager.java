package google;

public class GeminiApiManager {

    public static void main(String[] args) {

        final String GEMINI_API_KEY = "input your API key";

        if (GEMINI_API_KEY != null && !GEMINI_API_KEY.isEmpty()) {
            System.out.println("인증 성공");
        } else {
            System.out.println("키를 등록하세요");
        }
    }
}

