package tree.tree.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tree.commentReview.service.CommentReviewService;
import tree.review.dto.ReviewResponseDto;
import tree.review.service.ReviewService;
import tree.tree.dto.*;
import tree.tree.mapper.TreeMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TreeService {

    private final TreeMapper treeMapper;
    private final ReviewService reviewService;
    private final CommentReviewService commentReviewService;


    public List<TreeResponseDto> getTreeList(TreeRequestDto treeRequestDto){
        List<TreeResponseDto> treeList = treeMapper.getTreeList(treeRequestDto);
        if(!CollectionUtils.isEmpty(treeList)){
            for (TreeResponseDto tree : treeList) {
                String[] reviewImgList = null;
                if(tree.getReviewImgListString() != null){
                    reviewImgList = tree.getReviewImgListString().split(",");
                }
                tree.setTreeImgLimit3(reviewImgList);
            }
        }
        return treeList;
    }

    public List<TreeResponseDto> getRecommendTreeList(TreeRequestDto treeRequestDto){
        List<TreeResponseDto> treeList = treeMapper.getRecommendTreeList(treeRequestDto);
        if(!CollectionUtils.isEmpty(treeList)){
            for (TreeResponseDto tree : treeList) {
                String[] reviewImgList = null;
                if(tree.getReviewImgListString() != null){
                    reviewImgList = tree.getReviewImgListString().split(",");
                }
                tree.setTreeImgLimit3(reviewImgList);
            }
        }
        return treeList;
    }

    public List<TreeResponseDto> getTreeListBySearch(TreeRequestDto treeRequestDto){
        List<TreeResponseDto> treeList = treeMapper.getTreeListBySearch(treeRequestDto);
        if(!CollectionUtils.isEmpty(treeList)){
            for (TreeResponseDto tree : treeList) {
                String[] reviewImgList = null;
                if(tree.getReviewImgListString() != null){
                    reviewImgList = tree.getReviewImgListString().split(",");
                }
                tree.setTreeImgLimit3(reviewImgList);
            }
        }
        return treeList;

    }

    /**
     * 트리상세조회
     * @param treeDetailRequestDto
     * @return
     */
    public TreeDetailResponseDto getTree(TreeDetailRequestDto treeDetailRequestDto){
        TreeDetailResponseDto treeDetailResponseDto = treeMapper.getTree(treeDetailRequestDto);
        if(!ObjectUtils.isEmpty(treeDetailResponseDto)){
            //리뷰목록
            treeDetailResponseDto.setReviewList(reviewService.getReviewList(treeDetailRequestDto));
            //리뷰이미지리스트
            treeDetailResponseDto.setTreeImages(reviewService.getReviewImgList(treeDetailRequestDto));
            //코멘트리뷰목록
            treeDetailResponseDto.setCommentReviewList(commentReviewService.getCommentReview(treeDetailRequestDto));
            //선택한 코멘트 리스트
            treeDetailResponseDto.setMyComment(commentReviewService.getMyComment(treeDetailRequestDto));
        }

        return treeDetailResponseDto;
    }

    /**
     * 트리등록
     * @param treePostRequestDto
     * @return
     */
    public String insertTree(TreePostRequestDto treePostRequestDto){
        treeMapper.insertTree(treePostRequestDto);
        return Integer.toString(treePostRequestDto.getTreeId());
    }

    /**
     *
     */
    public boolean checkDuplTreeName(TreeDuplCheckDto treeDuplCheckDto){
        boolean isDupl = false;
        if(treeMapper.checkDuplTreeName(treeDuplCheckDto) > 0){
            isDupl = true;
        }else{
            isDupl=  false;
        }
        return isDupl;
    }

    public boolean checkDuplTreeMap(TreeDuplCheckDto treeDuplCheckDto){
        boolean isDupl = false;
        if(treeMapper.checkDuplTreeMap(treeDuplCheckDto) > 0){
            isDupl = true;
        }else{
            isDupl=  false;
        }
        return isDupl;
    }
}
