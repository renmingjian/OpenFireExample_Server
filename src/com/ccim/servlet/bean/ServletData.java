package com.ccim.servlet.bean;
/**
 * 结果查询后返回给客户端的JavaBean对象
 * @author Administrator
 *
 */
public class ServletData {
	
	private int code = 201;
	private String msg = "失败";
	private String type;
	private Object data;
	
	
	public String getType() {
		return type;
	}
	public ServletData setType(String type) {
		this.type = type;
		return this;
	}
	public int getCode() {
		return code;
	}
	public ServletData setCode(int code) {
		this.code = code;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ServletData setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public Object getData() {
		return data;
	}
	public ServletData setData(Object data) {
		this.data = data;
		return this;
	}
	@Override
	public String toString() {
		return "ServletData [code=" + code + ", msg=" + msg + ", type=" + type + ", data=" + data + "]";
	}
	
	
}
