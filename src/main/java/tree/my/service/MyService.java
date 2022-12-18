package tree.my.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tree.commentReview.mapper.CommentReviewMapper;
import tree.config.AuthDto;
import tree.my.dto.MyPageResponseDto;
import tree.my.dto.MyTreeResponseDto;
import tree.my.mapper.MyMapper;
import tree.review.dto.ReviewResponseDto;
import tree.tree.dto.TreeResponseDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyService {
    private final MyMapper myMapper;
    private final CommentReviewMapper commentReviewMapper;

    public MyPageResponseDto getMyPage(AuthDto authDto) {
        return myMapper.getMyPage(authDto);
    }

    public List<MyTreeResponseDto> getMyRegisteredTree(AuthDto authDto) {
        return myMapper.getMyRegisteredTree(authDto);
    }

    public List<MyTreeResponseDto> getMySavedTree(AuthDto authDto) {
        return myMapper.getMySavedTree(authDto);
    }

    public List<ReviewResponseDto> getMyReview(AuthDto authDto) {
        List<ReviewResponseDto> reviewList = myMapper.getMyReview(authDto);
        if(!CollectionUtils.isEmpty(reviewList)){
            for(ReviewResponseDto review : reviewList){
                review.setCommentList(commentReviewMapper.getCommentList(review));
            }
        }
        return reviewList;
    }
}
