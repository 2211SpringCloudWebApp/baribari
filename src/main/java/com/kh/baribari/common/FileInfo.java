package com.kh.baribari.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileUpload")
public class FileInfo {
	// 파일 저장
	public Map<String, String> saveFile(MultipartFile multi, HttpServletRequest request, String path) throws Exception {
		Map<String, String> result = null;
		if (multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();
			File file = new File("");
			// 업로드 한 파일의 실제 파일명
			String originalFileName = multi.getOriginalFilename();
			// 파일 서버의 프로젝트폴더 설정
			String rootPath = String.valueOf(file.getAbsoluteFile());
			// 경로를 가져와서 폴더의 경로 지정
			String savePath = rootPath + "\\src\\main\\resources\\static\\uploadFiles\\" + path;
			File folder = new File(savePath);
			// 저장할 폴더가 없을 경우 생성
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// UUID를 이용해 파일명 변경
			UUID uuid = UUID.randomUUID();
			// 저장되는 파일명 (uuid난수.확장자)
			String fileName = uuid.toString() + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			// 실제로 저장되는 경로 및 파일명
			String filePath = savePath + "/" + fileName;
			// DB에 저장되는 경로 및 파일명
			String dbSavePath = "/uploadFiles/" + path + "/" + fileName;
			// filePath에 파일 저장
			multi.transferTo(new File(filePath));

			result.put("original", originalFileName);
			result.put("fileName", fileName);
			result.put("filePath", dbSavePath);
		}
		return result;
	}

	// 파일 삭제
	public void deleteFile(HttpServletRequest request, String fileName) throws Exception {
		File file = new File("");
		// 파일 서버의 프로젝트폴더 설정
		String rootPath = String.valueOf(file.getAbsoluteFile());
		// 삭제할 경로
		String deletePath = rootPath + "\\src\\main\\resources\\static\\";
		String deleteFilePath = deletePath + "\\" + fileName;
		File deleteFile = new File(deleteFilePath);
		// deletaFilePath에 있는 파일 삭제
		if (deleteFile.exists()) {
			deleteFile.delete();
		}
	}
}
