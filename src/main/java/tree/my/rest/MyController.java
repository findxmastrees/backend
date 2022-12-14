package tree.my.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.config.exception.ApiException;
import tree.config.exception.ExceptionEnum;
import tree.my.service.MyService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {
    private final MyService myService;

    /**
     * 마이페이지 조회
     * @param
     * @return
     */
    @GetMapping
    public ResultDto getMyPage(@RequestParam("user_id") String id, HttpSession session) {
        AuthDto authDto = new AuthDto();
        ResultDto result = new ResultDto();
        String userId = "";

        if(!ObjectUtils.isEmpty((AuthDto)session.getAttribute("authDto"))){
            userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        }else{
            // Todo: 로그인 완성시 삭제
            userId = "admin";
            // Todo: 로그인 완성시 주석해제
            //throw new RuntimeException(new ApiException(ExceptionEnum.SECURITY_01));
        }

        authDto.setUserId(userId);
        result.setSuccess(true);
        result.setData(myService.getMyPage(authDto));
        return  result;
    }

    /**
     * 등록한 트리 조회
     * @param
     * @return
     */
    @GetMapping("/registeredtree")
    public ResultDto getMyRegisteredTree(@RequestParam("user_id") String id, HttpSession session) {
        AuthDto authDto = new AuthDto();
        String userId = "";
        ResultDto result = new ResultDto();

        if(!ObjectUtils.isEmpty((AuthDto)session.getAttribute("authDto"))){
            userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        }else{
            // Todo: 로그인 완성시 삭제
            userId = "admin";
            // Todo: 로그인 완성시 주석해제
            //throw new RuntimeException(new ApiException(ExceptionEnum.SECURITY_01));
        }

        authDto.setUserId(userId);
        result.setSuccess(true);
        result.setData(myService.getMyRegisteredTree(authDto));
        return  result;
    }

    /**
     * 저장한 트리 조회
     * @param
     * @return
     */
    @GetMapping("/savedtree")
    public ResultDto getMySavedTree(@RequestParam("user_id") String id, HttpSession session) {
        AuthDto authDto = new AuthDto();
        String userId = "";

        if(!ObjectUtils.isEmpty((AuthDto)session.getAttribute("authDto"))){
            userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        }else{
            // Todo: 로그인 완성시 삭제
            userId = "admin";
            // Todo: 로그인 완성시 주석해제
            //throw new RuntimeException(new ApiException(ExceptionEnum.SECURITY_01));
        }

        authDto.setUserId(userId);
        ResultDto result = new ResultDto();
        result.setSuccess(true);
        result.setData(myService.getMySavedTree(authDto));
        return  result;
    }

    /**
     * 내가 쓴 후기 조회
     * @param
     * @return
     */
    @GetMapping("/review")
    public ResultDto getMyReview(@RequestParam("user_id") String id, HttpSession session)  {
        AuthDto authDto = new AuthDto();
        String userId = "";

        if(!ObjectUtils.isEmpty((AuthDto)session.getAttribute("authDto"))){
            userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        }else{
            // Todo: 로그인 완성시 삭제
            userId = "admin";
            // Todo: 로그인 완성시 주석해제
            //throw new RuntimeException(new ApiException(ExceptionEnum.SECURITY_01));
        }

        authDto.setUserId(userId);
        ResultDto result = new ResultDto();
        result.setSuccess(true);
        result.setData(myService.getMyReview(authDto));
        return  result;
    }
}
