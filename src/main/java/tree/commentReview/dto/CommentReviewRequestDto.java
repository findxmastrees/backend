package tree.commentReview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.config.AuthDto;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentReviewRequestDto extends AuthDto {
    private List<String> commentIdList;
    private String treeId;
    @JsonIgnore
    private String commentId;
}
