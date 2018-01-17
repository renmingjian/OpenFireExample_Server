package com.ccim.servlet.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -2043134336526296863L;

	// 临时目录
	private String tempPath = "uploadtemp/";
	// 文件上传目录
	private String uploadPath = "upload/";
	// 服务器的绝对地址
	private String serverPath;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		serverPath = getServletContext().getRealPath("/").replace("\\", "/");
		System.out.println("服务器地址：" + serverPath);
		
		// 判断目录是否存在
		File tempFile = new File(serverPath + tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		File uploadFile = new File(serverPath + uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// 解析表单参数
		// 有两种
		// 第一种：普通的参数（一般都是String，说白了就是文本）
		// 第二种：非文本（例如：图片、文件）
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置最大缓存大小
		factory.setSizeThreshold(5 * 1024);
		// 指定临时文件上传目录
		factory.setRepository(tempFile);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传图片的最大值
		upload.setFileSizeMax(3 * 1024 * 1024);

		// 框架帮助我们解析表单数据，我们只需要获取就好了
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (!fileItem.isFormField()) {
					// 如果你不是一个普通参数，并且你是一个图片，那么就是我想要的，我就解析
					// 接下来上传---保证图片名称唯一
					String fileName = fileItem.getName().toLowerCase();
					String filePath = serverPath + uploadPath + fileName;
					fileItem.write(new File(filePath));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
