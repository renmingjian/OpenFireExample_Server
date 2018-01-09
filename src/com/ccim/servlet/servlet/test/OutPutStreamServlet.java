package com.ccim.servlet.servlet.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccim.servlet.servlet.BaseHttpServlet;

/**
 * Servlet implementation class OutPutStreamServlet
 */
public class OutPutStreamServlet extends BaseHttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			getData().setCode(200).setMsg("成功").setType("http://192.168.1.163:8080/WebApplication/erge.png");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		doWrite(response);
		
//		InputStream inputStream = null;
//		ServletOutputStream outputStream = null;
//		try {
//			// 获取图片在服务器的地址
//			String realPath = this.getServletContext().getRealPath("erge.png");
//			// 通过InputStream读取图片
//			inputStream = new FileInputStream(realPath);
//			outputStream = response.getOutputStream();
//			// 边读边写
//			int len = 0;
//			byte[] buffer = new byte[1024];
//			while ((len = inputStream.read(buffer)) > 0) {
//				outputStream.write(buffer, 0, len);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (outputStream != null) {
//					outputStream.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
	}

}
