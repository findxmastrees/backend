package tree.user.service;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tree.config.AuthDto;
import tree.user.config.KakaoOAuthDto;
import tree.user.mapper.UserMapper;

import java.io.*;
import java.net.*;

@Service
@RequiredArgsConstructor
public class SocialLoginService {

    private final KakaoOAuthDto kakaoOAuthDto;
    //private final KakaoInfoFeignClient kakaoInfoFeignClient;
    //private final KakaoLoginFeignClient kakaoLoginFeignClient;
    private static PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public String getKakaoAccessToken(String code) {
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
            sb.append("&redirect_uri=http://localhost:8080/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
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

    public AuthDto createKakaoUser(String accessToken) {
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
}