package tree.tree.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.config.AuthDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreePostRequestDto extends AuthDto {
    private int treeId;
    private String treeName;
    private String treeAddr;
    private String treeDetailAddr;
    @Min(value= -90, message = "위도는 -90~90사이여야 합니다.")
    @Max(value = 90, message = "위도는 -90~90사이여야 합니다.")
    private double treeX;
    @Min(value = -180, message = "경도는 -180~180사이여야 합니다.")
    @Max(value = 180, message = "경도는 -180~180사이여야 합니다.")
    private double treeY;
    private String outdoorYn;
    private String petYn;
    private String ableDays;
    private String startDate;
    private String endDate;
    private String etc;
}
