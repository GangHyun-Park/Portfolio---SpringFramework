package com.parkboard.board;

import java.util.List;
import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.parkboard.board.dao.BoardDao;
import com.parkboard.board.dto.BoardDto;
import com.parkboard.common.Pagination;
import com.parkboard.common.Search;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/root-context.xml",
		"classpath:spring/dataSource-context.xml"
		})

public class BoardDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);

	@Inject
	private BoardDao boardDAO;

	@Test @Ignore
	public void testGetBoardList() throws Exception {
		
		Pagination pagination = new Pagination();
		Search search = new Search();
		List<BoardDto> boardList = boardDAO.getBoardList(search);
		logger.info("\n Board List \n ");

		if(boardList.size() > 0) {
			for(BoardDto list : boardList) {
				logger.info(list.board_title);
			}
		} else {
			logger.info("데이터가 없습니다 !.");
		}
	}

	

	@Test @Ignore
	public void testGetBoardContent() throws Exception {

		BoardDto boardVO = boardDAO.getBoardDetail(1);

		logger.info("\n Board List \n ");

		if(boardVO != null) {

			logger.info("글번호 : " + boardVO.getBoard_num() );
			logger.info("글제목 : " + boardVO.getBoard_title() );
			logger.info("글내용 : " + boardVO.getBoard_content() );
			logger.info("글태그 : " + boardVO.getBoard_tag() );
			logger.info("조회수 : " + boardVO.getBoard_cnt() );
			logger.info("작성자 : " + boardVO.getBoard_writter() );
			logger.info("작성일 : " + boardVO.getBoard_date() );
			logger.info("수정일 : " + boardVO.getBoard_edit() );

		} else {
			logger.info("데이터가 없습니다 ~.");
		}
	}

	@Test @Ignore
	public void testInsertBoard() throws Exception {
		BoardDto boardVO = new BoardDto();
				
		boardVO.setBoard_cate("1");
		boardVO.setBoard_tag("1");
		boardVO.setBoard_writter("1");
		
		for( int i = 1; i < 1000 ; i++) {
		    boardVO.setBoard_title(i + " 번째 게시물 입니다.");
		    boardVO.setBoard_content(i + " 번째 게시물 입니다.");

		int result = boardDAO.insertBoard(boardVO);
		
		logger.info("\n Insert Board Result " +result);
		
		if(result == 1) {
			logger.info("\n 게시물 등록 성공 ");
		} else {
			logger.info("\n 게시물 등록 실패");
		}
		}
	}

	@Test @Ignore
	public void testUpdateBoard() throws Exception {

		BoardDto boardVO = new BoardDto();

		boardVO.setBoard_num(1);
		boardVO.setBoard_cate("1");
		boardVO.setBoard_title("첫번째 게시물 입니다-수정 합니다.");
		boardVO.setBoard_content("첫번째 게시물입니다-수정합니다.");
		boardVO.setBoard_tag("1-1");

		int result = boardDAO.updateBoard(boardVO);
		logger.info("\n Update Board Result \n ");

		if(result > 0) {
			logger.info("\n 게시물 수정 성공 ");
		} else {
			logger.info("\n 게시물 수정 실패");
		}
	}

	
	@Test @Ignore
	public void tesDeleteBoard() throws Exception {

		int result = boardDAO.deleteBoard(1);
		logger.info("\n Delete Board Result \n ");

		if(result > 0) {
			logger.info("\n 게시물 삭제 성공 ");
		} else {
			logger.info("\n 게시물 삭제 실패");
		}
	}

	@Test 
	public void testUpdateViewCnt() throws Exception {

		int result = boardDAO.updateBoardCnt(1);
		logger.info("\n Update View Count Result \n ");

		if(result > 0) {
			logger.info("\n 게시물 조회수 업데이트 성공 ");
		} else {
			logger.info("\n 게시물 조회수 업데이트 실패");
		}
	}
}
