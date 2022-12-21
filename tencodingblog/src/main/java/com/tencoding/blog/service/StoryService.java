package com.tencoding.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.repository.ImageRepository;

@Service
public class StoryService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	// ▼ "C:\\workspace_blog\\springbootwork\\upload\\"
	@Value("${file.path}")
	private String uploadFolder;
	
	public void imageUpload(RequestFileDto fileDto, PrincipalDetail detail) {
		
		UUID uuid = UUID.randomUUID();
		// 랜덤 변수
		System.out.println("uuid : " + uuid);
		
		String imageFileName = uuid + "_" + fileDto.getFile().getOriginalFilename();
		System.out.println(imageFileName);
		
		
		Path imageFilePath = Paths.get(
				uploadFolder + imageFileName);
		
		System.out.println("파일 패스 : " + imageFilePath);
		
		// 파일 만들기
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			// DB 저장 로직 추가
			
			Image image = fileDto.toEntity(imageFileName, detail.getUser());
			imageRepository.save(image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Transactional
	public Page<Image> getImageList(Pageable pageable) {
		
		return imageRepository.findAll(pageable);
		
	}
	
}
