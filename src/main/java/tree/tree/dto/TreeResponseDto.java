package tree.tree.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreeResponseDto {
    private String treeId;
    private String treeName;
    private String treeAddr;
    private String treeDetailAddr;
    private String treeX;
    private String treeY;
    private boolean treeLike; // 즐겨찾기여부
    private String[] treeImgLimit3; // 최신순 리뷰이미지3개
    @JsonIgnore
    private String reviewImgListString;
    private String distance; //거리
    private String reviewCnt; // 후기개수
}