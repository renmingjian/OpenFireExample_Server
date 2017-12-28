package com.ccim.servlet.bean;

import java.io.Serializable;

/**
 * 用户信息
 * @author Administrator
 *
 */
public class User implements Serializable {
	
	// 该用户在OpenFire中的jid（他是用户的唯一标识）
    private String jid;
    // 用户在自己平台的ID
	private int id;
	// 注册使用的账号，一般是手机号（也可以是其他）
	private String username;
	// 密码
	private String plainPassword;
	// 昵称
	private String nickname;
	// 用户头像
	private String user_img;
	// 用户自己设置的简介或者是签名
	private String desc;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getJid() {
		return jid;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	@Override
	public String toString() {
		return "User [jid=" + jid + ", id=" + id + ", username=" + username + ", plainPassword=" + plainPassword
				+ ", nickname=" + nickname + ", user_img=" + user_img + ", desc=" + desc + "]";
	}
	
	
	
	
}
