package com.parkboard.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.parkboard.board.dao.BoardDao;
import com.parkboard.board.dto.BoardDto;
import com.parkboard.board.dto.ReplyDto;
import com.parkboard.common.Search;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDao boardDao;
	
	@Override
	public List<BoardDto> getBoardList(Search search) throws Exception {
		return boardDao.getBoardList(search);
	}
	
	@Override
	public void insertBoard(BoardDto boardDto) throws Exception {
		boardDao.insertBoard(boardDto);
	}
	
	@Override
	public BoardDto getBoardDetail(int board_num) throws Exception {
		BoardDto boardDto = new BoardDto();
		boardDto = boardDao.getBoardDetail(board_num);
				
		return boardDto;
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		boardDao.updateBoard(boardDto);
		
	}
	
	@Override
	public void updateBoardCnt(int board_num) throws Exception {
		boardDao.updateBoardCnt(board_num);

	}
	
	@Override
	public void deleteBoard(int board_num) throws Exception {
		boardDao.deleteBoard(board_num);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return boardDao.getBoardListCnt(search);
	}

	@Override
	public List<ReplyDto> getReplyList(int board_num) throws Exception {
		return boardDao.getReplyList(board_num);
	}

	@Override
	public int insertReply(ReplyDto replyDto) throws Exception {
		return boardDao.insertReply(replyDto);
	}

	@Override
	public int update(ReplyDto replyDto) throws Exception {
		return boardDao.updateReply(replyDto);
	}

	@Override
	public int delete(int reply_num) throws Exception {
		return boardDao.deleteReply(reply_num);
	}

	@Override
	public int updateReply(ReplyDto replyDto) throws Exception {
		return boardDao.updateReply(replyDto);
	}

	@Override
	public int  deleteReply(int reply_num) throws Exception{
		return boardDao.deleteReply(reply_num);
	}

	
}
