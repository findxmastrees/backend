package tree.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoOAuthDto {

    private String infoUrl;     // kakao info 요청 url
    private String baseUrl;     // kakao auth 요청 url
    private String clientId;    // RestApi Key
    private String redirectUri; // 인가코드 받을 uri
    private String state;       // req-res 유효성 인증코드 (임의)
    private String SecretKey;

    /**
     * kakaoUrlInit()
     * @author  박누리
     * @desc    init 카카오 로그인 인가 코드 요청 url
     * @return  String
     */
    public String kakaoUrlInit() {
        Map<String,Object> params = new HashMap<>();
        params.put("client_id",getClientId());
        params.put("redirect_uri",getRedirectUri());
        params.put("response_type","code"); // 고정

        String paramStr = params.entrySet().stream()
                .map(param->param.getKey()+"="+param.getValue())
                .collect(Collectors.joining("&"));

        return getBaseUrl() + "/oauth/authorize" + "?" + paramStr;
    }
}
