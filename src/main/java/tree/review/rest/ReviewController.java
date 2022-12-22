package tree.review.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tree.config.ResultDto;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.dto.ReviewRequestDto;
import tree.review.dto.ReviewResponseDto;
import tree.review.service.ReviewService;
import tree.tree.dto.TreeDetailRequestDto;
import tree.tree.dto.TreeDetailResponseDto;
import tree.tree.dto.TreeDuplCheckDto;

import javax.validation.Valid;
import java.util.Map;

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
    public ResultDto insertReview(@Valid @ModelAttribute ReviewPostRequestDto reviewRequestDto) throws Exception {
        reviewRequestDto.setTreeId(reviewRequestDto.getTree_id());
        reviewRequestDto.setUserId(reviewRequestDto.getUser_id());
        reviewRequestDto.setCommentIdList(reviewRequestDto.getComment_id_list());
        return  reviewService.insertReview(reviewRequestDto);
    }

    @GetMapping("/{review_id}")
    public ResultDto getReview(@PathVariable("review_id") String review_id,@RequestParam Map<String, String> params ){
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        ReviewRequestDto reviewRequestDto = mapper.convertValue(params, ReviewRequestDto.class);
        reviewRequestDto.setReviewId(review_id);
        ResultDto resultDto = new ResultDto();
        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewRequestDto);
        resultDto.setSuccess(true);
        resultDto.setData(reviewResponseDto);
        return resultDto;
    }
}
