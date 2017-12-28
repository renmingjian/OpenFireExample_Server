package com.ccim.servlet.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ccim.servlet.bean.Friends;
import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.bean.User;

/**
 * 获取我的联系人列表
 * 该接口暂时没有用，获取联系人列表是从OpenFire获取的，此后可以修改使用这个接口
 */
public class MyFriendsServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		final ServletData data = getData();
		final QueryRunner runner = getRunner();
		final String jid = request.getParameter("jid");
		final String sqlQueryFriend = "select * from friends where current_jid=?";
		final List<User> userList = new ArrayList<>();
		try {
			final List<Friends> friendList = getRunner().query(sqlQueryFriend, 
					new BeanListHandler<Friends>(Friends.class), jid);
			if(friendList != null && friendList.size() > 0) {
				String friend_id = "";
				String sqlFriendInfo = "";
				User user = null;
				for(Friends friend : friendList) {
					friend_id = friend.getFriend_jid();
					sqlFriendInfo = "select * from ofuser where friend_id=?";
					user = runner.query(sqlFriendInfo, 
							new BeanHandler<User>(User.class), friend_id);
					if(user != null) {
						userList.add(user);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(userList.size() > 0) {
			data.setCode(200).setMsg("成功").setType("myFriends").setData(userList);
		} else {
			data.setCode(201).setMsg("无数据").setType("myFriends");
		}
		
		doWrite(response);
	}

}
