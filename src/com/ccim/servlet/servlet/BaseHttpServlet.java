package com.ccim.servlet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.DataSourceUtils;
import com.google.gson.Gson;

/**
 * 基类HttpServlet
 * 因为刚开始做有关java接口的代码，而接收和回传数据都是乱码，但是对request和response设置了
 * utf-8编码后就没有问题，所以把这个提取出来
 */
public abstract class BaseHttpServlet extends HttpServlet {
	
	private ServletData data;
	private Gson gson;
	private QueryRunner runner;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gson = new Gson();
		data = new ServletData();
		runner = new QueryRunner(DataSourceUtils.getDataSource());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8"); // 只是设置服务端的编码方式
		// 设置服务端编码方式的同时告知客户端的解码方式都是用utf-8
		response.setContentType("text/html;charset=UTF-8");
		
		handleGet(request, response);
	}

	/**
	 * post请求，默认调用get请求，所以不对post请求单独做处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 处理请求
	 */
	public abstract void handleGet(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 返回数据：
	 * 如果返回的数据只是文字，则使用该方法
	 */
	public void doWrite(HttpServletResponse response) {
		try {
			response.getWriter().write(gson.toJson(getData()));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("返回数据异常：" + e.toString());
		}
	}
	
	
	/**
	 * 返回数据:
	 * 如果返回的数据中有文件，则使用该方法返回数据
	 * （如果是只有App使用，则使用不到该方法，因为App下载文件只需要一个链接，如果是网页端使用，则可能使用到该方法）
	 */
	private void doOutPutStrean(HttpServletResponse response) {
		
	}

	/**
	 * 获取ServletData对象
	 */
	public ServletData getData() {
		return data;
	}
	
	/**
	 * 获取QueryRunner对象
	 */
	public QueryRunner getRunner() {
		return runner;
	}
}
