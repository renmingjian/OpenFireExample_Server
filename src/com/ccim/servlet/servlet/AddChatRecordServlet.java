package com.ccim.servlet.servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.DataSourceUtils;
import com.ccim.servlet.utils.DateUtils;
import com.ccim.servlet.utils.Utils;

/**
 * 增加聊天记录接口
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
public class AddChatRecordServlet extends BaseHttpServlet {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		// 定义回传数据的Bean
		final ServletData data = getData();

		// 获取传递过来的参数
		final String msg = request.getParameter("msg");
		final String msg_type = request.getParameter("msg_type");
		final String from_uid = request.getParameter("from_uid");
		final String to_uid = request.getParameter("to_uid");
	    String send_time = request.getParameter("send_time");
		
		System.out.println("添加聊天记录接收到参数：\n" + "from_uid = " + from_uid + ",to_uid = " + to_uid
				+ ",msg = " + msg + ", msg_type = " + msg_type + ", send_time = " + send_time);

		
		if(Utils.isNull(from_uid) || Utils.isNull(to_uid)) {
			data.setCode(201).setMsg("一方ID为空");
			return;
		}
		
		if(Utils.isNull(msg)) {
			data.setCode(202).setMsg("消息为空");
			return;
		}
		
		if(Utils.isNull(send_time)) {
			send_time = DateUtils.newDate();
		}
		
		final String sqlAddRecord = "insert into singlerecord(msg, msg_type, from_uid, to_uid, send_time) values(?, ?, ?, ?, ?)";
		try {
			int row = getRunner().update(sqlAddRecord, msg, msg_type, from_uid, to_uid, send_time);
			if(row > 0) {
				data.setCode(200).setMsg("数据插入成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			data.setMsg("数据插入失败");
		}
		// 想response中写入数据
		doWrite(response);
	}

}
