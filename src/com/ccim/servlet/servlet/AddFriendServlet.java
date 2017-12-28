package com.ccim.servlet.servlet;

import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ccim.servlet.bean.Friends;
import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.DataSourceUtils;
import com.ccim.servlet.utils.Utils;

/**
 * 添加好友
 * Servlet implementation class AddFriendServlet
 */
public class AddFriendServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		final ServletData data = getData();
		
		// 获取ID
		final String current_jid = request.getParameter("current_jid");
		final String friend_jid = request.getParameter("friend_jid");
		
		if(Utils.isNull(current_jid) || Utils.isNull(friend_jid)) {
			data.setCode(205).setMsg("一方id为空");
			return;
		}
		if(current_jid.equals(friend_jid)) {
			data.setCode(204).setMsg("添加的好友为自己");
			return;
		}
		
		// 查询数据库
		final String sqlQuery = "select * from friends where current_jid=?";
		Friends friend = null;
		try {
			friend = getRunner().query(sqlQuery, new BeanHandler<Friends>(Friends.class), current_jid);
		} catch (SQLException e) {
			e.printStackTrace();
			data.setCode(202).setMsg("数据库查询两者是否是已经是好友时出现异常");
			return;
		}
		System.out.println("查询好友结果：" + friend);
		
		if(friend == null || Utils.isNull(current_jid)) {
			final String sqlInsert = "insert into friends(current_jid, friend_jid) values(?, ?)";
			try {
				// 因为添加好友时互为好友，所以这里插入两条数据
				int row1 = getRunner().update(sqlInsert, current_jid, friend_jid);
				int row2 = getRunner().update(sqlInsert, friend_jid, current_jid);
				if(row1 > 0 && row2 > 0) {
					data.setCode(200).setMsg("添加好友成功");
				} else {
					data.setCode(203).setMsg("数据库添加两者为好友时插入异常");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				data.setCode(203).setMsg("数据库添加两者为好友时插入异常");
			}
		} else {
			data.setCode(201).setMsg("已经是好友");
		}
		
		doWrite(response);
	}


}
