package tree.user.dto;

import lombok.*;
import tree.user.config.KakaoOAuthDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoTokenRequestDto {

    private String clientId;
    private String code;
    private String clientSecret;
    private String redirectUri;
    private final String grantType = "authorization_code";

    public static KakaoTokenRequestDto newInstance(KakaoOAuthDto kakaoOAuthDto, String code){
        return KakaoTokenRequestDto.builder()
                .clientId(kakaoOAuthDto.getClientId())
                .clientSecret(kakaoOAuthDto.getSecretKey())
                .redirectUri(kakaoOAuthDto.getRedirectUri())
                .code(code)
                .build();
    }

    @Override
    public String toString(){

        return "code=" + code + "&" +
                "client_id=" + clientId + "&" +
                "client_secret=" + clientSecret + "&" +
                "redirect_uri=" + redirectUri + "&" +
                "grant_type=" + grantType;

    }

}
