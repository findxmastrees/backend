package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.user.dto.KakaoTokenRequestDto;
import tree.user.service.SocialLoginService;
import tree.user.service.UserService;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/social_login")
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class SocialLoginController {

    private final SocialLoginService socialLoginService;
    private final UserService userService;


    @ResponseBody
    @GetMapping("/kakao")
    public ResultDto kakaoCallback(@RequestParam String code) throws Exception {
        System.out.println(code);
        System.out.println("login ing.........");

        ResultDto resultDto = new ResultDto();
        int result = 0;
        String resultMsg = "";
        // get access_token by auth_code
        String access_token = socialLoginService.getKakaoAccessToken(code);

        // get kakaoUserInfo by access_token
        AuthDto authDto = socialLoginService.createKakaoUser(access_token);

        AuthDto memberDto = userService.getUserInfo(authDto.getUserId());
        // check user
        if(!ObjectUtils.isEmpty(memberDto)){
            // sign in
            authDto = userService.getUserInfo(authDto.getUserId());
            resultMsg = "로그인 성공";
            result = 1;
        } else {
            // sign up
            result = userService.createUserInfo(authDto);
            resultMsg = result == 1 ? "회원가입 성공":"회원가입 실패";
        }


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
