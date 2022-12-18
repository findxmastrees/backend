package tree.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tree.commentReview.service.CommentReviewService;
import tree.config.ResultDto;
import tree.review.dto.ReviewPostRequestDto;
import tree.review.dto.ReviewResponseDto;
import tree.review.mapper.ReviewMapper;
import tree.tree.dto.TreeDetailRequestDto;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	 private final ReviewMapper reviewMapper;
	 private final CommentReviewService commentReviewService;

	 public List<ReviewResponseDto> getReviewList(TreeDetailRequestDto treeDetailRequestDto){
		 List<ReviewResponseDto> reviewList = reviewMapper.getReviewList(treeDetailRequestDto);

		 if(!CollectionUtils.isEmpty(reviewList)){
			 for(ReviewResponseDto review : reviewList){
				 review.setCommentList(commentReviewService.getCommentList(review));
			 }
		 }


		 return reviewList;
	 }

	 public List<String> getReviewImgList(TreeDetailRequestDto treeDetailRequestDto){
		 return reviewMapper.getReviewImgList(treeDetailRequestDto);
	 }

	 public ResultDto insertReview( ReviewPostRequestDto reviewRequestDto) throws Exception {
		 ResultDto result  = new ResultDto();
		 if(reviewRequestDto.getImg() != null){
			 //5MB제한
			 double fileSize = reviewRequestDto.getImg().getSize();
			 if(fileSize / 1024 / 1024 >= 5){
				 result.setSuccess(false);
				 result.setMsg("파일사이즈는 5MB 이하여야 합니다.");
			 }
			 //***이미지업로드 api호출***
			 RestTemplate restTemplate = new RestTemplate();
			 String url = "https://api.imgbb.com/1/upload?key=5b420dcaaed10bc0f5d3ba8fbcb414e9";
			 //헤더
			 HttpHeaders httpHeaders = new HttpHeaders();
			 httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
			 //body
			 MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			 String imageString = getBase64String(reviewRequestDto.getImg());
			 body.add("image", imageString);

			 //이미지 등록 요청
			 HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
			 HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);

			 JSONParser jsonParser = new JSONParser();
			 JSONObject jsonObj = (JSONObject)jsonParser.parse(response.getBody());
			 JSONObject data = (JSONObject)jsonParser.parse(jsonObj.get("data").toString());

			 reviewRequestDto.setReviewImg(data.get("display_url").toString());
		 }


		 if(reviewMapper.insertReview(reviewRequestDto) > 0){
			 int commentCnt = 0;
			 if(!CollectionUtils.isEmpty(reviewRequestDto.getCommentIdList())){
				 commentCnt = commentReviewService.insertCommentReview(reviewRequestDto);
			 }

			result.setSuccess(true);
			Map<String,String> data = new HashMap<>();
			data.put("review_id",Integer.toString(reviewRequestDto.getReviewId()));
			data.put("comment_count" ,Integer.toString(commentCnt));
			result.setData(data);
		 }

		 return result;
	 }

	private String getBase64String(MultipartFile multipartFile) throws Exception {
		byte[] bytes = multipartFile.getBytes();
		return Base64.getEncoder().encodeToString(bytes);
	}


}
