package com.kh.baribari.community.communityPic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.community.communityPic.service.CommunityPicService;
import com.kh.baribari.community.domain.CommunityPIC;

@Controller
public class CommunityPicController {
	
	@Autowired	
	private CommunityPicService cService;
	
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileUpload;
	
	// 이미지 삭제
	@ResponseBody
	@PostMapping("deleteImg")
	public int deleteImg(
			@RequestParam("communityNo") int communityNo
			,@RequestParam("imageUrl") String imageUrl
			) throws Exception{
		CommunityPIC pic = new CommunityPIC();
		
		pic.setCommunityNo(communityNo);
		pic.setImageUrl(imageUrl);
		cService.deleteImg(pic); // 이미지 삭제
		
		pic = cService.showImg(communityNo); // 이미지가 삭제된 pic 가져옴.
		
		String[] pics = {pic.getCommunityPic1(), pic.getCommunityPic2(), pic.getCommunityPic3(),
                pic.getCommunityPic4(), pic.getCommunityPic5(), pic.getCommunityPic6(),
                pic.getCommunityPic7(), pic.getCommunityPic8(), pic.getCommunityPic9(),
                pic.getCommunityPic10()};
		
		for (int i = 0; i < pics.length - 1; i++) {
		    if (pics[i] == null) {
		        for (int j = i + 1; j < pics.length; j++) {
		            if (pics[j] != null) {
		                pics[i] = pics[j];
		                pics[j] = null;
		                break;
		            }
		        }
		    }
		}

		// 정렬된 pics 배열을 이용하여 새로운 pic 객체를 생성하거나, 기존 객체를 업데이트합니다.
		pic.setCommunityPic1(pics[0]);
		pic.setCommunityPic2(pics[1]);
		pic.setCommunityPic3(pics[2]);
		pic.setCommunityPic4(pics[3]);
		pic.setCommunityPic5(pics[4]);
		pic.setCommunityPic6(pics[5]);
		pic.setCommunityPic7(pics[6]);
		pic.setCommunityPic8(pics[7]);
		pic.setCommunityPic9(pics[8]);
		pic.setCommunityPic10(pics[9]);
		
		cService.ALLdeleteImg(communityNo);// 이미지를 전부 지움
		cService.registerPhoto(pic);
		return 0;
	}
	
	// 이미지 출력
	@ResponseBody
	@PostMapping("showImg")
	public CommunityPIC showImg(
			@RequestParam("communityNo") int communityNo
			) throws Exception{
		return cService.showImg(communityNo);
	}
	
	// 이미지 등록
	@ResponseBody
	@PostMapping("imgRegister")
	public int imgRegister(
			@RequestParam("file") MultipartFile[] files
			, @RequestParam("communityNo") int communityNo
			, HttpServletRequest request) throws Exception{
		
		int picCount = cService.getPicCount(communityNo); // 사진 갯수출력
		int picPoint = picCount + 1; // 사진이 저장될 위치
		int result = 0;
		
		String path = "community/board"; // 사진 경로
		CommunityPIC pic = new CommunityPIC();
		
		Map<String, String> fMap = new HashMap<String, String>();
		if(files.length + picCount < 11) { // 파일이 10개가 넘지 않을때
			for (MultipartFile file : files) {
				pic.setCommunityNo(communityNo);
				Map<String, String> fileInfo = fileUpload.saveFile(file, request, path);
				for (String k : fileInfo.keySet()) {// fileInfo 맵의 keySet() 메서드로 모든 키를 가져와서 변수 k에 하나씩 저장하는 반복문 시작
					String key = "file" + picPoint; // 문자열 "file"과 i를 합쳐서 변수 key에 저장
					String value = fileInfo.get(k);// fileInfo 맵에서 키 k에 해당하는 값을 가져와서 변수 value에 저장
					
					fMap.put(key, value);
					if (picPoint == 1) {
						pic.setCommunityPic1(value);
					} else if (picPoint == 2) {
						pic.setCommunityPic2(value);
					} else if (picPoint == 3) {
						pic.setCommunityPic3(value);
					} else if (picPoint == 4) {
						pic.setCommunityPic4(value);
					} else if (picPoint == 5) {
						pic.setCommunityPic5(value);
					} else if (picPoint == 6) {
						pic.setCommunityPic6(value);
					} else if (picPoint == 7) {
						pic.setCommunityPic7(value);
					} else if (picPoint == 8) {
						pic.setCommunityPic8(value);
					} else if (picPoint == 9) {
						pic.setCommunityPic9(value);
					} else if (picPoint == 10) {
						pic.setCommunityPic10(value);
					}
					
				}
				if(picCount > 0) {
					result = cService.updatePhoto(pic);
					pic = new CommunityPIC();
				}
				picPoint++;
			}
			
			if(picCount == 0) {
				result = cService.registerPhoto(pic);
			}
			return result;
		}else {
			return 9;
		}
	}
		
}
