package com.tencoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageble) {

		Page<Board> boards = boardService.getBoardList(pageble);

		int PAGENATION_BLOCK_COUNT = 2;

		// page.first / true, false
		// page.last / true, false
//		model.addAttribute("boards", boards);
		// jsp 파일에서 model 추상객체를 이용해 컨트롤러에게 내려 준 데이터 접근이 가능

		int nowPage = boards.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - PAGENATION_BLOCK_COUNT, 1);
		int endPage = Math.min(nowPage + PAGENATION_BLOCK_COUNT, boards.getTotalPages());

		System.out.println("화면에 보여주는 게시물 갯수 :: " + boards.getSize());
		System.out.println("전체 게시물 크기 :: " + boards.getTotalPages());
		System.out.println("Now : " + nowPage + "start!" + startPage);
		System.out.println(" end : " + endPage);

		ArrayList<Integer> pageNumbers = new ArrayList<>();

		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);

		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}

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

	@GetMapping("/board/{id}/update_form") // mapping의 변수명과 매개변수의 변수명을 다르게 하고싶은경우 name을 사용한다.
	public String updateForm(@PathVariable(name = "id") int borderId, Model model) {

		model.addAttribute("board", boardService.boardDetail(borderId));

		return "/board/update_form";
	}

}
