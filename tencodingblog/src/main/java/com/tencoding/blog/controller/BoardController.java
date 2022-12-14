package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageble) {

		Page<Board> boards = boardService.getBoardList(pageble);

		// page.first / true, false
		// page.last / true, false
		
		boards.stream().forEach(item -> {
			System.out.println(item);
		});

		model.addAttribute("boards", boards);
		// jsp 파일에서 model 추상객체를 이용해 컨트롤러에게 내려 준 데이터 접근이 가능

		System.out.println(boards);
		return "index";
	}

	@GetMapping("/board/save_form")
	public String saveForm() {

		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.boardDetail(id));
		
		return "/board/detail";
	}
	
	
	

}
