package tree.my.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyPageResponseDto {
    private String userId;
    private String nickname;
    private String userImg;
    private String registeredTreeCnt;//등록한 트리개수
    private String savedTreeCnt;// 저장한 트리개수
    private String registeredReviewCnt; // 내가 쓴 후기개수
}
