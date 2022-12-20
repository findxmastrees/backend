package tree.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인한 회원 정보
 */
@Getter
@Setter
public class AuthDto {
    private String userId;
    private String nickName;
    private String accessToken;
}
