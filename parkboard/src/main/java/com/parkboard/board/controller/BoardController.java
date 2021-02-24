package com.parkboard.board.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parkboard.board.dto.BoardDto;
import com.parkboard.board.dto.ReplyDto;
import com.parkboard.board.service.BoardService;
import com.parkboard.common.Search;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	/*���������� ��ȸ*/
	
	@RequestMapping(value="/skill", method = RequestMethod.GET)
	public String SkillPage(Model model) throws Exception{
		
		return "etc/skill";
	}
	/*�Խñ� ���*/
	@RequestMapping(value ="/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model, 
			@RequestParam(required = false, defaultValue = "1")int page, 
			@RequestParam(required = false, defaultValue = "1")int range,
			@RequestParam(required = false, defaultValue = "board_title")String searchType,
			@RequestParam(required = false)String keyword,
			@ModelAttribute("search")Search search) 
			throws Exception{
		
		//�˻��� ���
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		//�˻� �� ����¡ ó��
		int listCnt = boardService.getBoardListCnt(search);
		
		search.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination",search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		
		return "board/boardList";
	}
	
	/*�Խñ� �ۼ�*/
	@RequestMapping("/boardWrite")
	public String boardWrite(@ModelAttribute("boardDto")BoardDto boardDto,HttpSession session) {
		
		//���ǿ� ���̵� ���� �� ���������� �̵�
		Object check = session.getAttribute("sessionId");
		
		if(check == null)
		{
			return "etc/errorpage";
		}
		
		return "board/boardWrite";
	}
	
	/*�Խñ� ����*/
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(Model model,@ModelAttribute("BoardDto")BoardDto boardDto, 
			@RequestParam("mode")String mode, RedirectAttributes rttr)
	throws Exception{

		//���� or ������ �޾ƿͼ� �ٸ� service�� �̵�
		if(mode.equals("update")) {
			System.out.println("update ����");
			boardService.updateBoard(boardDto);
		} else {
			System.out.println("insert ����");
			boardService.insertBoard(boardDto);
		}
		
		
		return "board/getBoardList";
	}
	
	/*�Խñ� ����ȸ*/
	@RequestMapping(value = "/getBoardDetail", method = {RequestMethod.GET,RequestMethod.POST})
	public String getBoardDetail(HttpServletResponse response ,HttpServletRequest request ,BoardDto boardDto, Model model, @RequestParam("board_num")int board_num) throws Exception{
		
		//�Խñ۰� ��۸�� 
		model.addAttribute("boardDetail",boardService.getBoardDetail(board_num));
		model.addAttribute("replyDto",new ReplyDto());
		
		String cValue = String.valueOf(boardDto.getBoard_num());
		Cookie cookie = new Cookie("cNum", cValue);
		cookie.setMaxAge(10*60);
		Cookie[] isCookie = request.getCookies();
		
		if(isCookie[0].getValue().equals(cValue)) {
		}
		else {
			boardService.updateBoardCnt(board_num);
		}
		response.addCookie(cookie);
		//
		
		return "board/boardDetail";
	}
	
	/*�Խñ� ����*/
	@RequestMapping(value = "boardUpdate", method = RequestMethod.GET)
	public String boardUpdate(@RequestParam("board_num")int board_num, @RequestParam("mode") String mode, Model model,BoardDto boardDto)
	throws Exception{
		
		model.addAttribute("boardDetail",boardService.getBoardDetail(board_num));
		model.addAttribute("mode",mode);
		model.addAttribute("boardDto",new BoardDto());

		return "board/boardWrite";
	}
	
	/*�Խñ� ����*/
	@RequestMapping(value = "boardDelete", method = RequestMethod.GET)
	public String boardDelete(RedirectAttributes rttr, @RequestParam("board_num")int board_num) throws Exception{
		boardService.deleteBoard(board_num);
		return "redirect:/board/getBoardList";
	}
}
