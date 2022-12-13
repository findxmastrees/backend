package tree.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import tree.config.KakaoOAuthDto;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class SocialLoginService {

    private final KakaoOAuthDto kakaoOAuthDto;

    public HttpHeaders kakaoLogin(){
        return createHttpHeader(kakaoOAuthDto.kakaoUrlInit());
    }

    private static HttpHeaders createHttpHeader(String str){
        try {
            URI uri = new URI(str);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return httpHeaders;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
