package com.ccim.servlet.bean;

/**
 * 不适用userID：因为添加好友时只能拿到jid
 * @author Administrator 2017年12月26日 下午7:34:29
 *
 */
public class Friends {
	// 当前登录的用户jid
	private String current_jid;
	// 好友的jid
	private String friend_jid;

	public String getCurrent_jid() {
		return current_jid;
	}

	public void setCurrent_jid(String current_jid) {
		this.current_jid = current_jid;
	}

	public String getFriend_jid() {
		return friend_jid;
	}

	public void setFriend_jid(String friend_jid) {
		this.friend_jid = friend_jid;
	}

	@Override
	public String toString() {
		return "Friends [current_jid=" + current_jid + ", friend_jid=" + friend_jid + "]";
	}

}
