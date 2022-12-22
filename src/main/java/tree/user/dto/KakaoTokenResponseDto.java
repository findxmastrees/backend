package tree.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenResponseDto {
    private String accessToken;
    private String expiresIn;
    private String scope;
    private String refreshToken;
    private String tokenType;
    private String idToken;
    private String refreshTokenExpiresIn;

    public String getAccess_token(){
        return "Bearer " + accessToken;
    }

}
