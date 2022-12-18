package tree.commentReview.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentReviewResponseDto {
    private String commentId;
    private String comment; //코멘트명
    private String count;
    private String iconImg;
}
