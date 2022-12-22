package tree.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * KTokenInfoResponseDto
 * 카카오 로그인 사용자 정보
 */
@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenInfoResponseDto {
    private String id;
    private String expiresIn;
    private String appId;
}
