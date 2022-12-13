package tree.tree.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.config.AuthDto;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreePostRequestDto extends AuthDto {
    private int treeId;
    private String treeName;
    private String treeAddr;
    private String treeDetailAddr;
    private String treeX;
    private String treeY;
    private String outdoorYn;
    private String petYn;
    private String ableDays;
    private String startDate;
    private String endDate;
    private String etc;
}
