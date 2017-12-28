package com.ccim.servlet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 聊天记录
 * @author Administrator
 * 	| msg_id           | int(32)      | YES  |     | NULL    |消息ID
 *  | msg              | varchar(100) | YES  |     | NULL    |具体消息
 *  | msg_type         | varchar(32)  | YES  |     | NULL    |消息类型
 *  | current_uname    | varchar(32)  | YES  |     | NULL    |当前用户的名称
 *  | current_nickname | varchar(32)  | YES  |     | NULL    |当前用户的昵称
 *  | current_img      | varchar(100) | YES  |     | NULL    |当前用户的头像链接
 *  | other_uname      | varchar(32)  | YES  |     | NULL    |对方用户的名称
 *  | other_nickname   | varchar(32)  | YES  |     | NULL    |对方用户的昵称
 *  | other_img        | varchar(100) | YES  |     | NULL    |对方用户的头像链接
 *  | from_uid         | int(32)      | YES  |     | NULL    |消息是谁发送的：使用发送者的用户ID
 *  | to_uid           | int(32)      | YES  |     | NULL    |消息要发送给谁：使用对方的用户ID
 *  | send_time  
 *
 */
public class ChatRecord implements Serializable {
	
	private int msg_id;
	private int from_uid;
	private int to_uid;
	private String msg;
	private String msg_type;
	private String current_uname;
	private String current_nickname;
	private String current_img;
	private String other_uname;
	private String other_nickname;
	private String other_img;
	private String send_time;
	
	
	
	public int getMsg_id() {
		return msg_id;
	}
	public ChatRecord setMsg_id(int msg_id) {
		this.msg_id = msg_id;
		return this;
	}
	public int getFrom_uid() {
		return from_uid;
	}
	public ChatRecord setFrom_uid(int from_uid) {
		this.from_uid = from_uid;
		return this;
	}
	public int getTo_uid() {
		return to_uid;
	}
	public ChatRecord setTo_uid(int to_uid) {
		this.to_uid = to_uid;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ChatRecord setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public ChatRecord setMsg_type(String msg_type) {
		this.msg_type = msg_type;
		return this;
	}
	public String getCurrent_uname() {
		return current_uname;
	}
	public ChatRecord setCurrent_uname(String current_uname) {
		this.current_uname = current_uname;
		return this;
	}
	public String getCurrent_nickname() {
		return current_nickname;
	}
	public ChatRecord setCurrent_nickname(String current_nickname) {
		this.current_nickname = current_nickname;
		return this;
	}
	public String getCurrent_img() {
		return current_img;
	}
	public ChatRecord setCurrent_img(String current_img) {
		this.current_img = current_img;
		return this;
	}
	public String getOther_uname() {
		return other_uname;
	}
	public ChatRecord setOther_uname(String other_uname) {
		this.other_uname = other_uname;
		return this;
	}
	public String getOther_nickname() {
		return other_nickname;
	}
	public ChatRecord setOther_nickname(String other_nickname) {
		this.other_nickname = other_nickname;
		return this;
	}
	public String getOther_img() {
		return other_img;
	}
	public ChatRecord setOther_img(String other_img) {
		this.other_img = other_img;
		return this;
	}
	public String getSend_time() {
		return send_time;
	}
	public ChatRecord setSend_time(String send_time) {
		this.send_time = send_time;
		return this;
	}
	@Override
	public String toString() {
		return "ChatRecord [msg_id=" + msg_id + ", from_uid=" + from_uid + ", to_uid=" + to_uid + ", msg=" + msg
				+ ", msg_type=" + msg_type + ", current_uname=" + current_uname + ", current_nickname="
				+ current_nickname + ", current_img=" + current_img + ", other_uname=" + other_uname
				+ ", other_nickname=" + other_nickname + ", other_img=" + other_img + ", send_time=" + send_time + "]";
	}

}
