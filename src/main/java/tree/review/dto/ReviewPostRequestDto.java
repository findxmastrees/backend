package tree.review.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import tree.config.AuthDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewPostRequestDto extends AuthDto {
    @JsonIgnore
    private String treeId;
    @NotEmpty(message ="user_id는 필수값입니다.")
    private String user_id;
    @NotEmpty(message ="tree_id는 필수값입니다.")
    private String tree_id;
    @NotEmpty(message ="제목은 필수값입니다.")
    private String title;
    @NotEmpty(message ="내용은 필수값입니다.")
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
