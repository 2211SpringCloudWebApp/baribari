package com.kh.baribari.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

	public Map<String, String> saveFile(MultipartFile multi, HttpServletRequest request, String path) throws Exception {
		Map<String, String> result = null;
		if (multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();

			// 업로드 한 파일의 실제 파일명
			String originalFileName = multi.getOriginalFilename();
			// 파일을 서버의 resources폴더에 저장
			String root = request.getSession().getServletContext().getRealPath("resources");
			// 경로를 가져와서 폴더의 경로 지정: resources\설정된 경로
			String savePath = root + "\\" + path;
			
			File folder = new File(savePath);
			// 저장할 폴더가 없을 경우 생성
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			// UUID를 이용해 파일명 변경
			UUID uuid = UUID.randomUUID();
			// 저장되는 파일명 (uuid난수.확장자)
			String fileName = uuid.toString() + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			// 저장되는 경로 및 파일명
			String filePath = savePath + "\\" + fileName;
			
			// 파일 저장
			multi.transferTo(new File(filePath));
			
			result.put("original", originalFileName);
			result.put("fileName", fileName);
			result.put("filePath", filePath);
		}
		return result;
	}
	
}
