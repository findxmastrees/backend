package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.user.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * @Author 박누리
     * @Desc   사용자 로그인 처리
     * @return resultDto
     */
    @PostMapping("/login")
    public ResultDto login(){
        ResultDto resultDto = new ResultDto();

        return resultDto;
    }
/*    public ResultDto oAuthKakaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        ResultDto resultDto = null;



        //resultDto.setData();
        return resultDto;
    }*/

    /**
     * @Author  박누리
     * @Desc    사용자 회원가입 처리
     * @param   @RequestParam String nickname, HttpSession session
     * @return
     */
    @PostMapping("/signup")
    public ResultDto signup(@RequestParam String nickname, HttpSession session) throws Exception {
        System.out.println("[Controller]UserController.signup");
        ResultDto resultDto = new ResultDto();
        AuthDto authDto = (AuthDto) session.getAttribute("authDto");
        System.out.println("authDto.toString() : "+ authDto.toString());
        int result = 0;
        if(ObjectUtils.isEmpty(authDto) || authDto == null){
            resultDto.setSuccess(false);
            resultDto.setMsg("사용자 정보를 가져오는 중 문제가 발생하였습니다.");

            return resultDto;
        }

        authDto.setNickname(nickname);

        result = userService.createUserInfo(authDto);

        if(result > 0) {
            resultDto.setSuccess(true);
            resultDto.setMsg("signup success");
            resultDto.setData(authDto);

            session.setAttribute("authDto", authDto);
        } else {
            resultDto.setSuccess(false);
            resultDto.setMsg("회원가입 중 문제가 발생하였습니다.");
        }

        return resultDto;
    }
    /*public ResultDto getKaokaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        ResultDto resultDto = null;

        userService.getKaokaoLoginInfo(kakaoLoginRequestDto);

        return resultDto;
    }*/
}
