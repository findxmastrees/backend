package tree.star.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tree.config.ResultDto;
import tree.star.dto.StarRequestDto;
import tree.star.service.StarService;
import tree.tree.dto.TreeDetailResponseDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/star")
@RequiredArgsConstructor
public class StarController {
    private final StarService starService;

    /**
     * 즐겨찾기 등록/취소
     * @param starRequestDto
     * @return
     */
    @PostMapping
    public ResultDto changeStar(@Valid @RequestBody StarRequestDto starRequestDto) {

        return  starService.changeStar(starRequestDto);
    }
}
