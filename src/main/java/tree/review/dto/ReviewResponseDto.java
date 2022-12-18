package tree.review.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.commentReview.dto.CommentReviewResponseDto;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewResponseDto {
    public String reviewId;
    public String title;
    public String contents;
    public String reviewImg;
    public String regId;
    public String regDate;
    public String modId;
    public String modDate;
    public String userImg;
    private List<CommentReviewResponseDto> commentList; //선택한 코멘트리스트

}
