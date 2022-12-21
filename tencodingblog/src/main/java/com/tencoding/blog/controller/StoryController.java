package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@GetMapping("/home")
	public String storyHome(Model model, @PageableDefault(size = 4, sort = "id",
			direction = Direction.DESC) Pageable pageable) {
		
		Page<Image> imagePage = storyService.getImageList(pageable);
		
		System.out.println(imagePage.getContent());
		
		model.addAttribute("ImagePage", imagePage);
		
		
		return "story/home";
	}
	
	
	@GetMapping("/upload")
	public String storyUpload() {
		return "story/upload";
	}
	
	
	@PostMapping("/image/upload")
	// MultipartFile 1단게 : public String storyImgUpload(MultipartFile file, String storyText)
	/*System.out.println("getContext : " + file.getContentType());
	System.out.println("getName : " + file.getName());
	System.out.println("getOriginalFilename : " + file.getOriginalFilename());
	System.out.println("getResource : " + storyText);
	System.out.println("getSize : " + file.getSize());*/
	public String storyImgUpload(RequestFileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		

		storyService.imageUpload(fileDto, principalDetail);
		
		return "redirect:/";
		
	}

}
