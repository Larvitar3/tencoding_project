package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDTO;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public String save(Board board, 
			@AuthenticationPrincipal PrincipalDetail detail){
		
		boardService.write(board, detail.getUser());
		return "redirect:/";
		
	}
	
	/*
	 * @PostMapping("/api/board") public ModelAndView save(Board board,
	 * 
	 * @AuthenticationPrincipal PrincipalDetail detail){
	 * boardService.write(board, detail.getUser());
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/");
	 * 
	 * return mav;
	 * 
	 * }
	 */
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id) {
		
		boardService.deleteById(id);
		
		return new ResponseDTO<>(HttpStatus.OK, 1);
		
	}
	
	
	@PutMapping("/api/board/{boardId}")
	public ResponseDTO<Integer> update(@PathVariable int boardId, @RequestBody Board board){
		
		int result = boardService.modifyBoard(boardId, board);
		
		return new ResponseDTO<Integer>(HttpStatus.OK, result);
	}
	
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDTO<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reqReply,
			@AuthenticationPrincipal PrincipalDetail detail){
		
		boardService.writeReply(boardId, reqReply, detail.getUser());
		
		return new ResponseDTO<Integer>(HttpStatus.OK,1);
	}
	
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDTO<Integer> deleteRyplyById(@PathVariable String boardId, @PathVariable int replyId,
			@AuthenticationPrincipal PrincipalDetail principalDetail){
		
		System.out.println("1233123123123123");
		try {
			boardService.deleteReplyById(replyId, principalDetail.getUser().getId());
			System.out.println();
		} catch (Exception e) {
			
		}
		
		
		
		return new ResponseDTO<Integer>(HttpStatus.OK, 1);
		
	}
	
	
	
	
	
	
	
	
}
