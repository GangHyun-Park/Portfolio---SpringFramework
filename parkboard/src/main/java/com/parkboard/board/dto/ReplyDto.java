package com.parkboard.board.dto;

public class ReplyDto {

	public int reply_num;
	public int board_num;
	public String reply_content;
	public String reply_writter;
	public String reply_date;
	public String reply_edit;
	public String reply_mail;
	
	public String getreply_mail() {
		return reply_mail;
	}
	public void setreply_mail(String reply_mail) {
		this.reply_mail = reply_mail;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_writter() {
		return reply_writter;
	}
	public void setReply_writter(String reply_writter) {
		this.reply_writter = reply_writter;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getReplu_edit() {
		return reply_edit;
	}
	public void setReplu_edit(String reply_edit) {
		this.reply_edit = reply_edit;
	}
	
	
}
