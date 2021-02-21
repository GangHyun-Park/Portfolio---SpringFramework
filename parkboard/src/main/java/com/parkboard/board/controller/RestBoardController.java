package com.parkboard.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkboard.board.service.BoardService;
import com.parkboard.board.dto.ReplyDto;

@RestController
@RequestMapping(value = "/restboard")
public class RestBoardController {

	@Inject
	private BoardService boardService;
	
	
	//´ñ±Û ¸ñ·Ï
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST)
	public List<ReplyDto> getReplyList(@RequestParam("board_num")int board_num) throws Exception{
		return boardService.getReplyList(board_num);
	}
	
	//´ñ±Û ÀÔ·Â
	@RequestMapping(value = "/insertReply", method = RequestMethod.POST)
	public Map<String, Object> insertReply(@RequestBody ReplyDto replyDto) throws Exception{
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.insertReply(replyDto);
			result.put("status", "Okay");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "Bad");
		}
		return result;
	}
	
	//´ñ±Û ¼öÁ¤
	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public Map<String, Object> updateReply(@RequestBody ReplyDto replyDto) throws Exception{
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.updateReply(replyDto);
			result.put("status", "Okay");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "Bad");
		}
		return result;
	}
	
	//´ñ±Û »èÁ¦
	@RequestMapping(value = "deleteReply", method=RequestMethod.POST)
	public Map<String, Object> deleteReply(@RequestParam("reply_num")int reply_num) throws Exception{
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.deleteReply(reply_num);
			result.put("status", "Okay");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "Bad");
		}
		return result;
	}
}
