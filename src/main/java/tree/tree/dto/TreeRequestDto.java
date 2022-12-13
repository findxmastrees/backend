package tree.tree.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.config.AuthDto;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreeRequestDto extends AuthDto {
    private String mapX; // 현재위치 위도
    private String mapY; // 현재위치 경도
    private double northeastX; // 북동 위도
    private double northeastY; // 북동 경도
    private double southwestX; // 남서 위도
    private double southwestY; // 남서 경도
    private String searchParam;
}