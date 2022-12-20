package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tree.user.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * @Author 박누리
     * @Desc   카카오 로그인 토큰 저장
     * @return resultDto
     */
/*    public ResultDto oAuthKakaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        ResultDto resultDto = null;



        //resultDto.setData();
        return resultDto;
    }*/

    /**
     *
     * @param kakaoLoginRequestDto
     * @return
     */
    /*public ResultDto getKaokaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        ResultDto resultDto = null;

        userService.getKaokaoLoginInfo(kakaoLoginRequestDto);

        return resultDto;
    }*/
}
