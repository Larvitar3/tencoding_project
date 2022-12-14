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

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.ResponseDTO;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, 
			@AuthenticationPrincipal PrincipalDetail detail){
		System.out.println(board);
		
		boardService.write(board, detail.getUser());
		
		return new ResponseDTO<Integer>(HttpStatus.OK, 1);
	}
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}