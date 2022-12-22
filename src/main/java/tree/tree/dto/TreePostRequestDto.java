package tree.tree.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.config.AuthDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreePostRequestDto extends AuthDto {
    private int treeId;
    @NotBlank(message ="트리이름은 필수값입니다.")
    private String treeName;
    @NotBlank(message ="트리주소는 필수값입니다.")
    private String treeAddr;
    private String treeLoadAddr;
    private String treeDetailAddr;
    @NotNull(message ="위도는 필수값입니다.")
    @Min(value= -90, message = "위도는 -90~90사이여야 합니다.")
    @Max(value = 90, message = "위도는 -90~90사이여야 합니다.")
    private double treeX;
    @NotNull(message ="경도는 필수값입니다.")
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
