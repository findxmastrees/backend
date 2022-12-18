package tree.commentReview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tree.commentReview.dto.CommentReviewRequestDto;
import tree.commentReview.dto.CommentReviewResponseDto;
import tree.commentReview.mapper.CommentReviewMapper;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.dto.ReviewResponseDto;
import tree.review.service.ReviewService;
import tree.tree.dto.TreeDetailRequestDto;
import tree.tree.dto.TreePostRequestDto;
import tree.tree.dto.TreeRequestDto;
import tree.tree.dto.TreeResponseDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentReviewService {
    private final CommentReviewMapper commentReviewMapper;

    /**
     * 코멘트리뷰 등록
     * @param reviewPostRequestDto
     * @return
     */
    public int insertCommentReview(ReviewPostRequestDto reviewPostRequestDto){
        int cnt = 0;
        for(String commentId : reviewPostRequestDto.getCommentIdList()){
            reviewPostRequestDto.setCommentId(commentId);
            if(commentReviewMapper.insertCommentReview(reviewPostRequestDto) > 0){
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 리뷰의 코멘트리뷰 조회
     * @param reviewResponseDto
     * @return
     */
    public List<CommentReviewResponseDto> getCommentList(ReviewResponseDto reviewResponseDto) {
        return commentReviewMapper.getCommentList(reviewResponseDto);
    }


    /**
     * 트리의 전체 코멘트리뷰 조회
     * @param treeDetailRequestDto
     * @return
     */
    public List<CommentReviewResponseDto> getCommentReview(TreeDetailRequestDto treeDetailRequestDto) {
        return commentReviewMapper.getCommentReview(treeDetailRequestDto);
    }

    public List<CommentReviewResponseDto> getCommentReviewInitList(){
        return commentReviewMapper.getCommentReviewInitList();
    }

//    /**
//     * 본인이 선택한 코멘트 리뷰 조회
//     * @param treeDetailRequestDto
//     * @return
//     */
//    public int[] getMyComment(TreeDetailRequestDto treeDetailRequestDto) {
//        return commentReviewMapper.getMyComment(treeDetailRequestDto);
//    }

}
