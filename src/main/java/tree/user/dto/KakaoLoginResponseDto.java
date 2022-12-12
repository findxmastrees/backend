package tree.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLoginResponseDto {
    String kakaoToken;
    String email;
    String nickname;
}
