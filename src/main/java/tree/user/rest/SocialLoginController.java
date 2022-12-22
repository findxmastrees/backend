package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.user.service.SocialLoginService;
import tree.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class SocialLoginController {

    private final SocialLoginService socialLoginService;
    private final UserService userService;


    @GetMapping("/test")
    public ResultDto kakaoTest(@RequestParam String code, HttpServletRequest request){
        ResultDto resultDto = new ResultDto();

        System.out.println(code);

        // get access_token by auth_code
        String access_token = socialLoginService.getKakaoAccessToken(code,"http://whatevertree.herokuapp.com/oauth/test");

        if("".equals(access_token) || access_token == null){
            resultDto.setSuccess(false);
            resultDto.setMsg("카카오 인증시 문제가 발생하였습니다.");
            return resultDto;
        }

        resultDto.setSuccess(true);
        resultDto.setMsg("access_token :: "+ access_token);

        return resultDto;
    }

    @ResponseBody
    @GetMapping("/kakao")
    public ResultDto kakaoCallback(@RequestParam String code, HttpServletRequest request) throws Exception {
        System.out.println("[Controller]SocialLoginController.kakaoCallback");
        System.out.println(code);

        // 세션 생성
        HttpSession session = request.getSession();

        ResultDto resultDto = new ResultDto();
        int result = 0;
        String resultMsg = "";
        // get access_token by auth_code
        String access_token = socialLoginService.getKakaoAccessToken(code, "http://whatevertree.herokuapp.com/oauth/kakao");

        if("".equals(access_token) || access_token == null){
            resultDto.setSuccess(false);
            resultDto.setMsg("카카오 인증시 문제가 발생하였습니다.");
            return resultDto;
        }

        // get kakaoUserInfo by access_token
        AuthDto authDto = socialLoginService.getKakaoUserInfo(access_token);

        if(ObjectUtils.isEmpty(authDto)){
            resultDto.setSuccess(false);
            resultDto.setMsg("카카오 로그인 정보를 가져오는 중 문제가 발생하였습니다.");
            return resultDto;
        }

        AuthDto memberDto = userService.getUserInfo(authDto.getUserId());

        // check user
        if(!ObjectUtils.isEmpty(memberDto)){
            // sign in
            authDto.setUserId(memberDto.getUserId());
            authDto.setNickname(memberDto.getNickname());
            resultMsg = "login success";
            result = 1;
        } else {
            // sign up
            //result = userService.createUserInfo(authDto);
            resultMsg = "sign up";
            result = 1;
        }

        session.setAttribute("authDto", authDto);

        resultDto.setData(authDto);
        resultDto.setMsg(resultMsg);
        resultDto.setSuccess(result==1?true:false);

        return resultDto;
    }
    /**
     * 이하 추후 재적용
     */

   /*@GetMapping("/login/kakao/oauth")
   public Map<String,String> redirectKakaoLogin(@RequestParam(value="code") String code){
        return SocialLoginService.getKakaoTokenWithInfo(code);
   }*/
}
