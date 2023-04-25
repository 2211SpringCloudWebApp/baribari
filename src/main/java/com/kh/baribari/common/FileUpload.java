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
			File file = new File("");
	        // 업로드 한 파일의 실제 파일명
	        String originalFileName = multi.getOriginalFilename();
	        // 파일을 서버의 static 폴더에 저장
			String rootPath = String.valueOf(file.getAbsoluteFile());
	        // 경로를 가져와서 폴더의 경로 지정: static/설정된 경로
	        String savePath = rootPath+"\\src\\main\\resources\\static\\uploadFiles\\" + path;
			System.out.println("저장경로명 : "+savePath);
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
	        String filePath = savePath + "/" + fileName;
			String dbSavePath = "/uploadFiles/" + path + "/" + fileName;
	        // 파일 저장
	        multi.transferTo(new File(filePath));

			System.out.println("db에 저장될 경로+파일이름 : "+dbSavePath);

	        result.put("original", originalFileName);
	        result.put("fileName", fileName);
	        result.put("filePath", dbSavePath);
	    }
	    return result;
	}
	
	public void deleteFile(HttpServletRequest request, String fileName) throws Exception {
		File file = new File("");
		String rootPath = String.valueOf(file.getAbsoluteFile());
		String deletePath = rootPath+"\\src\\main\\resources\\static\\";
		String deleteFilePath = deletePath + "\\" + fileName;
		File deleteFile = new File(deleteFilePath);
		System.out.println(deleteFile);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
	}
	
}
