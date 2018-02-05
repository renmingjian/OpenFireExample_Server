package com.ccim.servlet.servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.bean.User;
import com.ccim.servlet.utils.DataSourceUtils;

/**
 * 登录接口
 */
public class LoginServlet extends BaseHttpServlet {

		@Override
		public void handleGet(HttpServletRequest request, HttpServletResponse response) {
			final ServletData data = getData();

			// 获取用户名和密码
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");

			// 查询数据库获取结果
			final String sql = "select * from user where username=? and plainPassword=?";
			User user = null;
			try {
				user = getRunner().query(sql, new BeanHandler<User>(User.class), username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (user == null) {
				data.setMsg("该用户不存在！");
			} else {
				data.setCode(200).setMsg("登录成功").setType("login").setData(user);
			}
			// 向response中写入数据
			doWrite(response);
			System.out.println("返回的结果：" + data.toString());
		}	
}
