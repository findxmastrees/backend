package tree.review.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.dto.ReviewRequestDto;
import tree.review.dto.ReviewResponseDto;
import tree.tree.dto.TreeDetailRequestDto;

import java.util.List;

@Mapper
public interface ReviewMapper {
	List<ReviewResponseDto> getReviewList (TreeDetailRequestDto treeDetailRequestDto);
	List<String> getReviewImgList (TreeDetailRequestDto treeDetailRequestDto);
	int insertReview(ReviewPostRequestDto reviewPostRequestDto);
	ReviewResponseDto getReview(ReviewRequestDto reviewRequestDto);
}
