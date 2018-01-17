package com.ccim.servlet.servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.Utils;

/**
 * 注册接口
 * 
 * @author Administrator
 *
 */
public class RegisterServlet extends BaseHttpServlet {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		final ServletData data = getData();
		try {
			// 1.获取请求传递过来的键值对
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			final String email = request.getParameter("email");
			final String jid = request.getParameter("jid");
			System.out.println("接收到的数据：" + username + "--" + password);
			
			if(Utils.isNull(username) || Utils.isNull(password)) {
				data.setCode(202).setMsg("用户名或者密码为空");
				doWrite(response);
				return;
			}

			// 2.对数据库进行操作
			final String sqlRegist = "insert into ofuser(username, plainPassword, email, jid) values(?, ?, ?, ?)";
			int count = getRunner().update(sqlRegist, username, password, email, jid);
			if (count > 0) {
				data.setCode(200).setMsg("注册成功").setType("register");
			} else {
				data.setCode(201).setMsg("注册失败").setType("register");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("注册失败：" + e.toString());
		}
		// 向response中写入数据
		doWrite(response);
		System.out.println("注册返回信息：" + data.toString());		
	}
}
