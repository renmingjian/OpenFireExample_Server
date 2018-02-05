package com.ccim.servlet.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.bean.User;
import com.ccim.servlet.utils.DataSourceUtils;

/**
 * 聊天查询用户信息
 * 需要把用户ID用“-”进行拼接，当前用户最好放在最前面
 */
public class ChatUserInfoServlet extends BaseHttpServlet {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		final ServletData data = getData();
		data.setType("chatUserInfo");
		
		// 获取请求参数
		final String jids = request.getParameter("jids");
		final String[] arrayUids = jids.split("-");
		
		
		System.out.println("获取的参数：" + jids + "---转换后的参数：" + arrayUids.toString());
		
		String sql = "";
		List<User> userList = new ArrayList<>();
		User user = null;
		if(arrayUids != null) {
			int arrayLength = arrayUids.length;
			for (int i = 0; i < arrayLength; i++) {
				sql = "select * from user where jid=?";
				try {
					user = getRunner().query(sql, new BeanHandler<User>(User.class), arrayUids[i]);
					if (user != null) {
						userList.add(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("查询失败：" + e.toString());
				}
			}
		}
		if(userList.size() > 0) {
			data.setCode(200).setMsg("成功").setData(userList);
		}
		// 想response中写入数据
		doWrite(response);
		System.out.println("查询结果：" + data.toString());
	}

}
