package tree.tree.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tree.config.exception.ApiException;
import tree.config.exception.ExceptionEnum;
import tree.tree.dto.*;
import tree.tree.service.TreeService;
import tree.config.ResultDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;

    /**
     * 지도 범위 내 트리 목록 조회
     * @param params 북동좌표, 남서 좌표
     * @return
     */
    @GetMapping
    public ResultDto getTreeList(@RequestParam Map<String, String> params) {
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeRequestDto treeRequestDto = mapper.convertValue(params, TreeRequestDto.class);

        ResultDto resultDto = new ResultDto();
        List<TreeResponseDto> treeResponseDto = treeService.getTreeList(treeRequestDto);
        resultDto.setSuccess(true);
        resultDto.setData(treeResponseDto);

        return resultDto;
    }

    /**
     * 현재위치 기준 10km이내 후기 많은 순으로 3개 조회, 후기 개수 같을경우 가까운 순
     * @param params 북동좌표, 남서 좌표
     * @return
     */
    @GetMapping("/recommend")
    public ResultDto getRecommendTreeList(@RequestParam Map<String, String> params) {
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeRequestDto treeRequestDto = mapper.convertValue(params, TreeRequestDto.class);

        ResultDto resultDto = new ResultDto();
        List<TreeResponseDto> treeResponseDto = treeService.getRecommendTreeList(treeRequestDto);
        resultDto.setSuccess(true);
        resultDto.setData(treeResponseDto);

        return resultDto;
    }

    /**
     * 검색한 트리 목록 조회 - 현재위치 데이터 있을 경우 가까운 순, 없을 경우 리뷰많은순
     * @return
     */
    @GetMapping("/search")
    public ResultDto getTreeListBySearch(@RequestParam Map<String, String> params){
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeRequestDto treeRequestDto = mapper.convertValue(params, TreeRequestDto.class);

        ResultDto resultDto = new ResultDto();
        List<TreeResponseDto> treeResponseDto = treeService.getTreeListBySearch(treeRequestDto);
        resultDto.setSuccess(true);
        resultDto.setData(treeResponseDto);

        return resultDto;
    }

    /**
     * 트리 상세조회
     * @param
     * @return
     */
    @GetMapping("/{tree_id}")
    public ResultDto getTree(@PathVariable("tree_id") String treeId, @RequestParam Map<String, String> params){
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeDetailRequestDto treeDetailRequestDto = mapper.convertValue(params, TreeDetailRequestDto.class);
        treeDetailRequestDto.setTreeId(treeId);
        ResultDto resultDto = new ResultDto();
        TreeDetailResponseDto treeDetailResponseDto = treeService.getTree(treeDetailRequestDto);
        resultDto.setSuccess(true);
        resultDto.setData(treeDetailResponseDto);
        return resultDto;
    }

    /**
     * 트리 등록
     * @param treePostRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity insertTree(@Valid @RequestBody TreePostRequestDto treePostRequestDto){
        ResultDto resultDto = new ResultDto();
        String treeId = treeService.insertTree(treePostRequestDto);
        HashMap<String,String> map = new HashMap();
        map.put("tree_id", treeId);
        resultDto.setData(map);
        resultDto.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    /**
     * 트리 이름 중복체크
     */
    @GetMapping("/check/name")
    public ResultDto checkDuplTreeName( @RequestParam Map<String, String> params){
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeDuplCheckDto treeDuplCheckDto = mapper.convertValue(params, TreeDuplCheckDto.class);

        boolean isDupl = treeService.checkDuplTreeName(treeDuplCheckDto);
        ResultDto resultDto = new ResultDto();
        if(isDupl){
            resultDto.setSuccess(false);
            resultDto.setMsg("중복된 이름");
        }else{
            resultDto.setSuccess(true);
            resultDto.setMsg("사용가능한 이름");
        }
        return resultDto;
    }

    /**
     * 트리 위치 중복체크
     */
    @GetMapping("/check/map")
    public ResultDto checkDuplTreeMap( @RequestParam Map<String, String> params){
        // jackson 라이브러리의 ObjectMapper 클래스를 이용하여  Snake Case -> Camel Case
        ObjectMapper mapper = new ObjectMapper();
        TreeDuplCheckDto treeDuplCheckDto = mapper.convertValue(params, TreeDuplCheckDto.class);

        boolean isDupl = treeService.checkDuplTreeMap(treeDuplCheckDto);
        ResultDto resultDto = new ResultDto();
        if(isDupl){
            resultDto.setSuccess(false);
            resultDto.setMsg("100m이내에 이미 트리가 존재합니다.");
        }else{
            resultDto.setSuccess(true);
            resultDto.setMsg("등록 가능 트리");
        }
        return resultDto;
    }
}
