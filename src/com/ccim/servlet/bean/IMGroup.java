package com.ccim.servlet.bean;

/**
 * 群组信息
 * 
 * @author Administrator 2018年1月16日 上午10:19:00
 *
 */
public class IMGroup {

	// 群组名称
	private String groupname;
	// 群组密码
	private String grouppassword;
	// 群组群成员数量
	private String groupnumber;
	// 群组简介
	private String gorupdesc;

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupnumber() {
		return groupnumber;
	}

	public void setGroupnumber(String groupnumber) {
		this.groupnumber = groupnumber;
	}

	public String getGrouppassword() {
		return grouppassword;
	}

	public void setGrouppassword(String grouppassword) {
		this.grouppassword = grouppassword;
	}

	public String getGorupdesc() {
		return gorupdesc;
	}

	public void setGorupdesc(String gorupdesc) {
		this.gorupdesc = gorupdesc;
	}

	@Override
	public String toString() {
		return "IMGroup [groupname=" + groupname + ", grouppassword=" + grouppassword + ", groupnumber=" + groupnumber
				+ ", gorupdesc=" + gorupdesc + "]";
	}
}
