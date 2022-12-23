package tree.review.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tree.config.AuthDto;
import tree.config.ResultDto;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.service.ReviewService;
import tree.tree.dto.TreeDuplCheckDto;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰등록
     * @param
     * @return
     */
    @PostMapping
    public ResultDto insertReview(@Valid @ModelAttribute ReviewPostRequestDto reviewRequestDto, HttpSession session) throws Exception {
        String userId = ((AuthDto)session.getAttribute("authDto")).getUserId();
        reviewRequestDto.setTreeId(reviewRequestDto.getTree_id());
        reviewRequestDto.setUserId(userId);
        reviewRequestDto.setCommentIdList(reviewRequestDto.getComment_id_list());
        return  reviewService.insertReview(reviewRequestDto);
    }
}
