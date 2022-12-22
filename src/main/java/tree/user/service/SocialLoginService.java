package tree.user.service;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tree.config.AuthDto;
import tree.user.config.KakaoOAuthDto;
import tree.user.mapper.UserMapper;

import java.io.*;
import java.net.*;

@Service
@RequiredArgsConstructor
@ConfigurationProperties
public class SocialLoginService {

    //private final KakaoOAuthDto kakaoOAuthDto;
    //private final KakaoInfoFeignClient kakaoInfoFeignClient;
    //private final KakaoLoginFeignClient kakaoLoginFeignClient;
    //private static PasswordEncoder passwordEncoder;

    /**
     * 카카오로그인 - access_token 받기
     * @param code
     * @return
     */
    public String getKakaoAccessToken(String code, String redirectUrl) {
        String accessToken = "";
        String refreshToken = "";
        String reqUrl = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("grant_type=authorization_code");
            sb.append("&client_id=6e8e0e1b1c81cbfb29ac2e7cc2576b6e"); // TODO REST_API_KEY 입력
            //sb.append("&redirect_uri=http://localhost:8080/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            //sb.append("&redirect_uri=http://whatevertree.herokuapp.com/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&redirect_uri="+redirectUrl); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            sb.append("&client_secret=" + "a3lNifTx4xim2mKIUXbbjyuTbfoQM1YU");

            bw.write(sb.toString());
            bw.flush();
            System.out.println("sb: "+sb.toString());

            int resCode = conn.getResponseCode();
            System.out.println("responseCode: " + resCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);

            br.close();
            bw.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accessToken;
    }

    /**
     * 카카오로그인 - 카카오 사용자정보 조회
     * @param accessToken
     * @return
     */
    public AuthDto getKakaoUserInfo(String accessToken) {
        AuthDto authDto = new AuthDto();

        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        //get user info by access_token
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer "+accessToken);

            System.out.println("accessToken: "+accessToken);
            int resCode = conn.getResponseCode();
            System.out.println("resCode: "+resCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("res body: "+ result);

            JsonElement element = JsonParser.parseString(result);

            String id = element.getAsJsonObject().get("id").getAsString();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";

            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            System.out.println("id: "+id);
            System.out.println("email: "+email);

            authDto.setAccessToken(accessToken);
            authDto.setUserId(id);

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return authDto;
    }

    /**
     * 카카오 계정 연결 끊기 (회원탈퇴)
     * @param accessToken
     * @return
     */
    public String unlinkKakao(String accessToken){
        System.out.println("[Service]"+this.getClass().getName()+"::unlinkKakao");
        String reqUrl = "https://kapi.kakao.com/v2/user/unlink";
        String id = "";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer "+accessToken);

            System.out.println("accessToken: "+accessToken);
            int resCode = conn.getResponseCode();
            System.out.println("resCode: "+resCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("res body: "+ result);

            JsonElement element = JsonParser.parseString(result);

            id = element.getAsJsonObject().get("id").getAsString();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    /**
     * 카카오 계정 로그아웃
     * @return
     */
    public int logoutKakao(String userId, String logoutRedirectURL) throws Exception {
        System.out.println("[Service]"+this.getClass().getName()+"::logoutKakao");
        String state = "azzul";
        String reqUrl = "https://kauth.kakao.com/oauth/logout";
        int result = 0;

        URL url = new URL(reqUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder sb = new StringBuilder()
                .append("client_id="+userId)
                .append("&logout_redirect_uri="+logoutRedirectURL)
                .append("&state="+state);
        //?client_id=${YOUR_REST_API_KEY}&logout_redirect_uri=${YOUR_LOGOUT_REDIRECT_URI}"

        bw.write(sb.toString());
        bw.flush();

        int resCode = conn.getResponseCode();
        System.out.println("resCode: " + resCode);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";

        if((line = br.readLine()) != null) {
            line = br.readLine();
        }

        result = (state.equals(JsonParser.parseString(line).getAsJsonObject().get("state").getAsString())) ? 1 : 0;

        conn.disconnect();
        bw.close();
        br.close();

        return result;
    }
}