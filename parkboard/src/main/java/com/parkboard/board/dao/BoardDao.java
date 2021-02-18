package com.parkboard.board.dao;

import java.util.List;

import com.parkboard.board.dto.BoardDto;
import com.parkboard.board.dto.ReplyDto;
import com.parkboard.common.Search;


public interface BoardDao {
	
	public List<BoardDto> getBoardList(Search search) throws Exception;
	public BoardDto getBoardDetail(int board_num) throws Exception;
	public int insertBoard(BoardDto boardDto) throws Exception;
	public int updateBoard(BoardDto boardDto) throws Exception;
	public int deleteBoard(int board_num) throws Exception;
	public int updateBoardCnt(int board_num) throws Exception;
	public int getBoardListCnt(Search search) throws Exception;
	
	//´ñ±Û±â´É
	public List<ReplyDto> getReplyList(int board_num) throws Exception;
	public int insertReply(ReplyDto replyDto) throws Exception;
	public int updateReply(ReplyDto replyDto) throws Exception;
	public int deleteReply(int reply_num) throws Exception;
	

}
