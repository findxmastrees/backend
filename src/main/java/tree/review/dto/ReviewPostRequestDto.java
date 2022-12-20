package tree.review.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import tree.config.AuthDto;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewPostRequestDto extends AuthDto {
    @JsonIgnore
    private String treeId;
    private String user_id;
    private String tree_id;
    private String title;
    private String contents;
    private MultipartFile img;
    @JsonIgnore
    private String reviewImg;
    @JsonIgnore
    private int reviewId;
    @JsonIgnore
    private List<String> commentIdList;
    private List<String> comment_id_list;
    @JsonIgnore
    private String commentId;
}
