package com.parkboard.board.service;

import java.util.List;

import com.parkboard.board.dto.BoardDto;
import com.parkboard.board.dto.ReplyDto;
import com.parkboard.common.Search;

public interface BoardService {
	
	public List<BoardDto> getBoardList(Search search) throws Exception;
	public void insertBoard(BoardDto boardDto) throws Exception;
	public BoardDto getBoardDetail(int board_num) throws Exception;
	public void updateBoard(BoardDto boardDto) throws Exception;
	public void updateBoardCnt(int board_num) throws Exception;
	public void deleteBoard(int board_num) throws Exception;
	public int getBoardListCnt(Search search) throws Exception;

	//´ñ±Û
	
	public List<ReplyDto> getReplyList(int board_num) throws Exception;
	public int insertReply(ReplyDto replyDto) throws Exception;
	public int update(ReplyDto replyDto) throws Exception;
	public int delete(int reply_num) throws Exception;
	public int updateReply(ReplyDto replyDto) throws Exception;
	public int deleteReply(int reply_num) throws Exception;
}
