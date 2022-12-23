package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.user.service.SocialLoginService;
import tree.user.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SocialLoginService socialLoginService;

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
    public ResultDto signup(@RequestBody String nickname, HttpSession session) throws Exception {
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

        result = userService.checkNickname(nickname);

        if(result == 0) {
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
        } else {
            resultDto.setSuccess(false);
            resultDto.setMsg("exist nickname");
        }

        return resultDto;
    }
    /*public ResultDto getKaokaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        ResultDto resultDto = null;

        userService.getKaokaoLoginInfo(kakaoLoginRequestDto);

        return resultDto;
    }*/
    @GetMapping("/userInfo")
    public ResultDto getUserInfo(HttpSession session){
        ResultDto resultDto = new ResultDto();
        AuthDto authDto = (AuthDto) session.getAttribute("authDto");
        if(!ObjectUtils.isEmpty(authDto)){
            resultDto.setSuccess(true);
            resultDto.setMsg("사용자 정보 가져오기 성공");
            resultDto.setData(authDto);

        } else {
            resultDto.setSuccess(false);
            resultDto.setMsg("사용자 정보를 가져오는 중 문제가 발생하였습니다.");
        }

        return resultDto;
    }

    @GetMapping("/check/nickname")
    public ResultDto checkNickname (String nickname) throws Exception {
        ResultDto resultDto = new ResultDto();
        int result = 0;

        result = userService.checkNickname(nickname);

        if(result > 0) {
            resultDto.setSuccess(false);
            resultDto.setMsg("exist nickname");
        } else {
            resultDto.setSuccess(true);
            resultDto.setMsg("사용가능한 닉네임입니다.");
        }

        return resultDto;
    }
    @GetMapping("/logout")
    public ResultDto logout(@RequestParam String logoutRedirectURL, HttpSession session) throws Exception {
        ResultDto resultDto = new ResultDto();
        int result = 0;
        AuthDto authDto = (AuthDto) session.getAttribute("AuthDto");
        String userId = authDto.getUserId();

        session.invalidate();

        result = socialLoginService.logoutKakao(userId, logoutRedirectURL);

        if(result == 1){
            resultDto.setSuccess(true);
            resultDto.setMsg("logout success");
        } else {
            resultDto.setSuccess(false);
            resultDto.setMsg("logout fail");
        }

        return resultDto;
    }
    @GetMapping("/logoutTest")
    public void logoutTest(HttpSession session) throws Exception {
        System.out.println("[Controller]UserController::logoutTest");
    }


    @GetMapping("/signout")
    @Transactional
    public ResultDto signout(HttpSession session) throws Exception {
        ResultDto resultDto = new ResultDto();
        int result = 0;

        AuthDto authDto = (AuthDto) session.getAttribute("authDto");
        String userId =  authDto.getUserId();
        String accessToken = authDto.getAccessToken();

        if(accessToken == null || accessToken == "") {
            resultDto.setSuccess(false);
            resultDto.setMsg("유효하지 않은 로그인입니다. 재로그인 하세요.");

            return resultDto;
        } else {
            // 카카오 사용자 끊기
            String unlinkUserId = socialLoginService.unlinkKakao(accessToken);

            if(userId.equals(unlinkUserId)){
                // 어쩔트리 사용자 데이터 삭제
                result = userService.deleteUserInfo(userId);
            }
        }

        if(result > 0){
            resultDto.setSuccess(true);
            resultDto.setMsg("회원 탈퇴 완료");
        } else {
            resultDto.setSuccess(false);
            resultDto.setMsg("회원 탈퇴 중 문제가 발생하였습니다.");
        }

        return resultDto;
    }
}
