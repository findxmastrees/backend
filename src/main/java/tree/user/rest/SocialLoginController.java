package tree.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tree.config.ResultDto;
import tree.user.dto.KakaoTokenRequestDto;
import tree.user.service.SocialLoginService;

@RestController
@RequestMapping("/social_login")
@RequiredArgsConstructor
public class SocialLoginController {

    private final SocialLoginService socialLoginService;

    /*@ResponseBody
    @GetMapping("/oauth/kakao")
    public ResultDto oAuthKakao(KakaoTokenRequestDto kakaoTokenRequestDto){
        ResultDto resultDto;

        return resultDto;
    }*/

    /**
     * kakaoLogin()
     * @Author  박누리
     * @Desc    카카오 로그인
     * @return  ResponseEntity
     */
    @GetMapping("/login/kakao")
    public ResponseEntity<Object> kakaoLogin(){
        HttpHeaders httpHeaders = socialLoginService.kakaoLogin();

        return httpHeaders != null ?
                new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER)
                :ResponseEntity.badRequest().build();
   }
}
