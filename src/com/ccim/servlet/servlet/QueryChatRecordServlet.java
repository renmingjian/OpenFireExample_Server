package com.ccim.servlet.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ccim.servlet.bean.ChatRecord;
import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.bean.User;
import com.ccim.servlet.utils.DataSourceUtils;
import com.ccim.servlet.utils.Utils;

/**
 * 查询聊天记录
 * 
 * 
 *    Field            | Type         | Null | Key | Default | 
 *  +------------------+--------------+------+-----+---------+
 *  | msg_id           | int(32)      | YES  |     | NULL    |消息ID
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
 *  | send_time        | varchar(32)  | YES  |     | NULL    |发送的时间
 */
public class QueryChatRecordServlet extends BaseHttpServlet {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		// 定义回传数据的Bean
		final ServletData data = getData();
		
		// 获取传递过来的参数
		final String msg = request.getParameter("msg");
		final String msg_type = request.getParameter("msg_type");
		final String from_uid = request.getParameter("from_uid");
		final String to_uid = request.getParameter("to_uid");
		final String send_time = request.getParameter("send_time");
		
		System.out.println("查询聊天记录接收到参数：\n" + "from_uid = " + from_uid + ",to_uid = " + to_uid
				+ ",msg = " + msg + ", msg_type = " + msg_type + ", send_time = " + send_time);

		if(Utils.isNull(from_uid) || Utils.isNull(to_uid)) {
			data.setCode(201).setMsg("一方id为空");
			return;
		}
		
		// 查询用户数据
		final String sqlQueryUser = "select * from user where id=?";
		User userFrom = null;
		User userTo = null;
		final ChatRecord record = new ChatRecord();
		record.setFrom_uid(Integer.valueOf(from_uid)).setTo_uid(Integer.valueOf(to_uid));
		try {
			userFrom = getRunner().query(sqlQueryUser, new BeanHandler<User>(User.class), from_uid);
			userTo = getRunner().query(sqlQueryUser, new BeanHandler<User>(User.class), to_uid);
			if (userFrom != null) {
				record.setCurrent_img(userFrom.getUser_img()).setCurrent_nickname(userFrom.getNickname())
						.setCurrent_uname(userFrom.getUsername());
			}
			if (userTo != null) {
				record.setOther_img(userTo.getUser_img()).setOther_nickname(userTo.getNickname())
						.setOther_uname(userTo.getUsername());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询用户失败：" + e.toString());
		}
		
		// 查询聊天数据
		final String sqlQueryRecord = "selcet * from singlerecord where from_uid=? and to_uid=?";
		// 聊天记录集合
	    List<ChatRecord> recordList = new ArrayList<>();
		try {
			recordList = getRunner().query(sqlQueryRecord, new BeanListHandler<ChatRecord>(ChatRecord.class), from_uid, to_uid);
			data.setData(recordList).setCode(200);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询聊天记录失败：" + e.toString());
		}
		
		// 向response中写入数据
		doWrite(response);
	}

}
