package tree.review.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tree.config.ResultDto;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.service.ReviewService;
import tree.tree.dto.TreeDuplCheckDto;

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
    public ResultDto insertReview(ReviewPostRequestDto reviewRequestDto) throws Exception {
        reviewRequestDto.setTreeId(reviewRequestDto.getTree_id());
        reviewRequestDto.setUserId(reviewRequestDto.getUser_id());
        reviewRequestDto.setCommentIdList(reviewRequestDto.getComment_id_list());
        return  reviewService.insertReview(reviewRequestDto);
    }
}
