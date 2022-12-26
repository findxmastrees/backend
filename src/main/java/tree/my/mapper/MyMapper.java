package tree.my.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.config.AuthDto;
import tree.my.dto.MyPageResponseDto;
import tree.my.dto.MyReviewResponseDto;
import tree.my.dto.MyTreeResponseDto;
import tree.review.dto.ReviewResponseDto;
import tree.tree.dto.TreeResponseDto;

import java.util.List;

@Mapper
public interface MyMapper {
    MyPageResponseDto getMyPage(AuthDto authDto);
    List<MyTreeResponseDto> getMyRegisteredTree(AuthDto authDto);
    List<MyTreeResponseDto> getMySavedTree(AuthDto authDto);
    List<MyReviewResponseDto> getMyReview(AuthDto authDto);
}
