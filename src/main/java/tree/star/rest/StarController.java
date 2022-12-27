package tree.star.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.star.dto.StarRequestDto;
import tree.star.service.StarService;
import tree.tree.dto.TreeDetailResponseDto;

import javax.servlet.http.HttpSession;
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
    public ResultDto changeStar(@Valid @RequestBody StarRequestDto starRequestDto, HttpSession session) {
        String userId = "";

        if(!ObjectUtils.isEmpty((AuthDto)session.getAttribute("authDto"))){
            userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        }else{
            // Todo: 로그인 완성시 삭제
            userId = "admin";
            // Todo: 로그인 완성시 주석해제
            //throw new RuntimeException(new ApiException(ExceptionEnum.SECURITY_01));
        }
        starRequestDto.setUserId(userId);

        return  starService.changeStar(starRequestDto);
    }
}
