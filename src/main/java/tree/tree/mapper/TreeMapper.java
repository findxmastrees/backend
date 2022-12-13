package tree.tree.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.tree.dto.*;

import java.util.List;

@Mapper
public interface TreeMapper {
    List<TreeResponseDto> getTreeList(TreeRequestDto treeRequestDto);
    List<TreeResponseDto> getRecommendTreeList(TreeRequestDto treeRequestDto);
    List<TreeResponseDto> getTreeListBySearch(TreeRequestDto treeRequestDto);
    TreeDetailResponseDto getTree(TreeDetailRequestDto treeDetailRequestDto);
    void insertTree(TreePostRequestDto treePostRequestDto);
}
