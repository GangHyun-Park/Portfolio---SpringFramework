package com.parkboard.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.parkboard.board.dto.BoardDto;
import com.parkboard.board.dto.ReplyDto;
import com.parkboard.common.Search;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDto> getBoardList(Search search) throws Exception {
		return sqlSession.selectList("com.parkboard.board.mappers.boardMapper.getBoardList",search);
	}

	@Override
	public BoardDto getBoardDetail(int board_num) throws Exception {
		return sqlSession.selectOne("com.parkboard.board.mappers.boardMapper.getBoardDetail",board_num);
	}

	@Override
	public int insertBoard(BoardDto boardDto) throws Exception {
		return sqlSession.insert("com.parkboard.board.mappers.boardMapper.insertBoard", boardDto);
	}

	@Override
	public int updateBoard(BoardDto boardDto) throws Exception {
		return sqlSession.update("com.parkboard.board.mappers.boardMapper.updateBoard", boardDto);
	}

	@Override
	public int deleteBoard(int board_num) throws Exception {
		return sqlSession.insert("com.parkboard.board.mappers.boardMapper.deleteBoard",board_num);
	}

	@Override
	public int updateBoardCnt(int board_num) throws Exception {
		return sqlSession.update("com.parkboard.board.mappers.boardMapper.updateBoardCnt",board_num);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return sqlSession.selectOne("com.parkboard.board.mappers.boardMapper.getBoardListCnt",search);
	}

	@Override
	public List<ReplyDto> getReplyList(int board_num) throws Exception {
		return sqlSession.selectList("com.parkboard.board.mappers.replyMapper.getReplyList",board_num);
	}

	@Override
	public int insertReply(ReplyDto replyDto) throws Exception {
		return sqlSession.insert("com.parkboard.board.mappers.replyMapper.insertReply",replyDto);
	}

	@Override
	public int updateReply(ReplyDto replyDto) throws Exception {
		return sqlSession.update("com.parkboard.board.mappers.replyMapper.updateReply",replyDto);
	}

	@Override
	public int deleteReply(int reply_num) throws Exception {
		return sqlSession.delete("com.parkboard.board.mappers.replyMapper.deleteReply",reply_num);
	}

}
