package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	public void write(Board board, User user) {

		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		Page<Board> page = boardRepository.findAll(pageable);
		System.out.println(page.getSize());

		return boardRepository.findAll(pageable);
	}

	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});

	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public int modifyBoard(int boardId, Board board) {

		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});

		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 해당 함수 종료 시점에 트랜잭션이 종료가 되고 더티 체킹 후 commit 처리를 한다.

		return 1;
	}

	@Transactional
	public void writeReply(int boardId, Reply reqReply, User user) {

		// 영속성
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		});

		reqReply.setUser(user);
		reqReply.setBoard(board);

		replyRepository.save(reqReply);

	}

	@Transactional
	public void deleteReplyById(int replyId, int reqUserId) {

		System.out.println("reqId " + reqUserId);

		Reply reply = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글을 찾을 수 없습니다.");
		});

		try {
			int dbWriter = reply.getUser().getId();
			int principal = reqUserId;

			if (dbWriter == principal) {
				replyRepository.deleteById(replyId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		// replyRepository.deleteById(replyId);
	}

	public Page<Board> searchBoard(String q, Pageable pageable) {
		return boardRepository.findByTitleContaining(q, pageable);

	}

}
