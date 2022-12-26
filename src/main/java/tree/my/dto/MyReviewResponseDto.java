package tree.my.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.commentReview.dto.CommentReviewResponseDto;
import tree.review.dto.ReviewResponseDto;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyReviewResponseDto extends ReviewResponseDto {

    private String treeName;
    private String treeAddr;
    private String treeLoadAddr;
}
