package tree.commentReview.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tree.commentReview.dto.CommentReviewRequestDto;
import tree.commentReview.service.CommentReviewService;
import tree.config.ResultDto;
import tree.tree.dto.TreePostRequestDto;
import tree.tree.dto.TreeRequestDto;
import tree.tree.dto.TreeResponseDto;
import tree.tree.service.TreeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentReviewController {
    private final CommentReviewService commentReviewService;

    /**
     * 코멘트 등록 - 등록시마다 기존 코멘트 있으면 삭제후 재등록
     * @param
     * @return
     */
    @PostMapping
    public ResultDto insertCommentReview(@RequestBody CommentReviewRequestDto commentReviewRequestDto){
        ResultDto resultDto = new ResultDto();
        int cnt = commentReviewService.insertCommentReview(commentReviewRequestDto);
        HashMap<String,String> map = new HashMap();
        map.put("count", Integer.toString(cnt));
        resultDto.setData(map);
        resultDto.setSuccess(true);
        return resultDto;
    }
}
