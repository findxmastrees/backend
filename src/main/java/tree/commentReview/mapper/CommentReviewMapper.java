package tree.commentReview.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.commentReview.dto.CommentReviewRequestDto;
import tree.commentReview.dto.CommentReviewResponseDto;
import tree.tree.dto.TreeDetailRequestDto;

import java.util.List;

@Mapper
public interface CommentReviewMapper {
    int insertCommentReview(CommentReviewRequestDto commentReviewRequestDto);
    int deleteCommentReview(CommentReviewRequestDto commentReviewRequestDto);
    List<CommentReviewResponseDto> getCommentReview(TreeDetailRequestDto treeDetailRequestDto) ;
    int[] getMyComment(TreeDetailRequestDto treeDetailRequestDto) ;
}
