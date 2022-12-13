package tree.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoTokenResponseDto {
    String kakaoToken;
    String email;
    String nickname;
}
