package tree.review.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.review.dto.ReviewResponseDto;
import tree.tree.dto.TreeDetailRequestDto;

import java.util.List;

@Mapper
public interface ReviewMapper {
	List<ReviewResponseDto> getReviewList (TreeDetailRequestDto treeDetailRequestDto);
	List<String> getReviewImgList (TreeDetailRequestDto treeDetailRequestDto);
}
