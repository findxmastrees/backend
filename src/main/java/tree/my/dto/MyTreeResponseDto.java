package tree.my.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyTreeResponseDto {
    private String treeId;
    private String treeName;
    private String treeAddr;
    private String treeDetailAddr;
    private String treeX;
    private String treeY;
    private String reviewCnt; // 후기개수
}
