package tree.tree.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import tree.commentReview.dto.CommentReviewResponseDto;
import tree.review.dto.ReviewImgResponseDto;
import tree.review.dto.ReviewResponseDto;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TreeDetailResponseDto extends TreeResponseDto{
	private List<ReviewResponseDto> reviewList; //리뷰 목록
	private int treeImgCnt; // 트리이미지개수
	private List<ReviewImgResponseDto> treeImages; // 리뷰이미지 리스트
	private List<CommentReviewResponseDto> commentReviewList; //코멘트리뷰목록
	private String outdoorYn;
	private String petYn;
	private String ableDays;
	private String startDate;
	private String endDate;
	private String etc;
}
