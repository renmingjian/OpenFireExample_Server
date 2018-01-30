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

import com.ccim.servlet.bean.ServletData;
import com.ccim.servlet.utils.Constants;
import com.ccim.servlet.utils.Utils;
import com.google.gson.Gson;

/**
 * 上传文件
 * 
 * @author Administrator 2018年1月19日 上午10:44:34
 *
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -2043134336526296863L;

	// 临时目录
	private String tempPath = "uploadtemp/";
	// 文件上传目录
	private String uploadPath = "upload/";
	// 上传的类型
	private String uploadType;
	private static final String UPLOAD_VOICE = "voice"; // 语音文件
	private static final String UPLOAD_FILE = "file"; // 其他文件
	private static final String UPLOAD_VIDEO = "video"; // 视频文件
	private static final String UPLOAD_IMG = "img"; // 图片文件
	// 服务器的绝对地址
	private String serverPath;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");

		final ServletData data = new ServletData();
		// 存放成功后要返回文件的路径
		String url = Constants.BASE_URL + uploadPath;
		// 判断缓存目录是否存在
		final File tempFile = new File(serverPath + tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		// 文件要保存的路径
		serverPath = getServletContext().getRealPath("/").replace("\\", "/") + uploadPath;

		// 解析表单参数
		// 有两种
		// 第一种：普通的参数（一般都是String，说白了就是文本）
		// 第二种：非文本（例如：图片、文件）
		final DiskFileItemFactory factory = new DiskFileItemFactory();
		final ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大缓存大小
		factory.setSizeThreshold(5 * 1024);
		// 指定临时文件上传目录
		factory.setRepository(tempFile);
		// 设置上传图片的最大值
		upload.setFileSizeMax(3 * 1024 * 1024);

		// 框架帮助我们解析表单数据，我们只需要获取就好了
		List<FileItem> fileItems = null;
		String fileName = "";
		try {
			fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {

				// 文本参数：这个参数中有type值，根据type创建不同的目录，保存对应的文件
				if (fileItem.isFormField()) {
					// 获取参数的键值对
//					System.out.println("fileName = " + fileItem.getFieldName());
//					System.out.println("value = " + fileItem.getString());
					uploadType = fileItem.getString();
					
					System.out.println("地址1：" + serverPath);
					if (!Utils.isNull(uploadType)) {
						data.setType(uploadType);
						serverPath = serverPath + uploadType + "/";
					}
					System.out.println("地址2：" + serverPath);
					File uploadFile = new File(serverPath);
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
				}

				// 文件参数
				if (!fileItem.isFormField()) {
					// 如果你不是一个普通参数，并且你是一个图片，那么就是我想要的，我就解析
					// 接下来上传---保证图片名称唯一
					fileName = fileItem.getName().toLowerCase();
					System.out.println("serverPath = " + serverPath + fileName);
					System.out.println("fileName = " + fileName);
					fileItem.write(new File(serverPath + fileName));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 在data字段中返回该文件的URL
		url = url + uploadType + "/" + fileName;
		System.out.println("返回的URL = " + url);
		data.setCode(200).setMsg("成功").setData(url);
		resp.getWriter().write(new Gson().toJson(data));
	}
}
