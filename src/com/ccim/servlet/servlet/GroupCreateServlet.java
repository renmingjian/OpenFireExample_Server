package com.ccim.servlet.servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ccim.servlet.bean.IMGroup;
import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.Utils;

/**
 * 创建群组--保存创建的群组消息
 */
public class GroupCreateServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		final ServletData data = getData();
		data.setType("groupCreate");
		final String groupname = request.getParameter("groupname");
		final String gorupdesc = request.getParameter("gorupdesc");
		// 密码可有可无
		final String grouppassword = request.getParameter("grouppassword");
		
		System.out.println("接收参数--创建群组：groupname = " + groupname + ", gorupdesc = " + gorupdesc);
		
		if(Utils.isNull(groupname)) {
			data.setCode(201).setMsg("群组名称为空");
			doWrite(response);
			return;
		}
		
		if(Utils.isNull(gorupdesc)) {
			data.setCode(202).setMsg("群组简介为空");
			doWrite(response);
			return;
		}
		
		final String sqlGroupQuery = "select * from groups where groupname=?";
		IMGroup group = null;
		try {
			group = getRunner().query(sqlGroupQuery, new BeanHandler<IMGroup>(IMGroup.class), groupname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(group != null) {
			data.setCode(203).setMsg("群组已经存在").setData(group);
			doWrite(response);
			return;
		}
		
		final String sqlGroupInsert = "insert into groups(groupname, gorupdesc, grouppassword) values(?, ?, ?)";
		try {
			int count = getRunner().update(sqlGroupInsert, groupname, gorupdesc, grouppassword);
			if(count > 0) {
				data.setCode(200).setMsg("群组创建成功");
			} else {
				data.setCode(204).setMsg("插入数据为0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			data.setCode(205).setMsg("插入数据库失败");
		}
		
		doWrite(response);
	}
       
}
