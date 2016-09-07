package com.jeff.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mUtils.FileOperateUtil;
import mUtils.ResponseUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeff.model.ResponseToApp;

@Controller
public class FileUploadController extends BaseController {
	// 获取文件上传需要保存的路径，upload文件夹需存在。
	String path = "F://shareApp_data";
	String path2 = "F://shareApp_data/headPic/";
	String fileName;

	@RequestMapping(value = "upload.do")
	public void upload(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("uploading");

		request.setCharacterEncoding("utf-8");
		// 获得磁盘文件条目工厂。
		@SuppressWarnings("deprecation")
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
		factory.setRepository(new File(path));
		// 设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
		factory.setSizeThreshold(1024 * 1024);
		// 上传处理工具类（高水平API上传处理？）
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 获取表单属性名字。
				String name = item.getFieldName();
				System.out.println("uploading-getFiledname:" + name);

				// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
				if (item.isFormField()) {
					// 获取用户具体输入的字符串，
					String value = item.getString();
					request.setAttribute(name, value);
				}
				// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
				else {
					// 获取路径名
					String value = item.getName();
					System.out.println("uploading-getName:" + value);
					// 取到最后一个反斜杠。
					int start = value.lastIndexOf("\\");
					// 截取上传文件的 字符串名字。+1是去掉反斜杠。
					String filename = value.substring(start + 1);

					System.out.println("uploading-filename:" + filename);
					request.setAttribute(name, filename);

					/*
					 * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
					 */

					System.out.println("uploading--filename:" + filename);
					path += addPrefix(filename) + "/";
					// 收到写到接收的文件中。
					OutputStream out = new FileOutputStream(new File(path,
							filename));
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("获取文件总量的容量:" + item.getSize());

					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();

					mRespopnseData = new ResponseToApp("200", "success", 1,
							addPrefix(filename) +"/"+ filename);

					try {
						ResponseUtils.resposeToApp(resp, mRespopnseData);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String addPrefix(String name) {
		// TODO Auto-generated method stub
		if (name.endsWith("jpg") || name.endsWith("png"))
			return "/picResource";
		if (name.endsWith("mp4") || name.endsWith("3gp"))
			return "/videoResource";
		if (name.endsWith("doc") || name.endsWith("txt")
				|| name.endsWith("docx"))
			return "/textResource";
		if (name.endsWith("ppt") || name.endsWith("pptx"))
			return "/pptResource";
		if (name.endsWith("mp3") || name.endsWith("arm"))
			return "/voiceResource";
		else
			return "/otherResource";

	}

	@RequestMapping(value = "upload_image.do")
	public void uploadImage(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("uploading");

		request.setCharacterEncoding("utf-8");
		// 获得磁盘文件条目工厂。
		@SuppressWarnings("deprecation")
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
		factory.setRepository(new File(path2));
		// 设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
		factory.setSizeThreshold(1024 * 1024);
		// 上传处理工具类（高水平API上传处理？）
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 获取表单属性名字。
				String name = item.getFieldName();
				System.out.println("uploading:" + name);

				// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
				if (item.isFormField()) {
					// 获取用户具体输入的字符串，
					String value = item.getString();
					request.setAttribute(name, value);
				}
				// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
				else {
					// 获取路径名
					String value = item.getName();
					System.out.println("uploading:" + value);
					fileName = value;
					// 取到最后一个反斜杠。
					int start = value.lastIndexOf("\\");
					// 截取上传文件的 字符串名字。+1是去掉反斜杠。
					String filename = value.substring(start + 1);
					request.setAttribute(name, filename);

					/*
					 * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
					 */
					// 收到写到接收的文件中。
					OutputStream out = new FileOutputStream(new File(path2,
							filename));
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("获取文件总量的容量:" + item.getSize());

					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		mRespopnseData = new ResponseToApp("200", "success", 1, "/upload/"
				+ fileName);

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	// @RequestMapping(value = "download")
	// public void download(HttpServletRequest request,
	// HttpServletResponse response) {
	// init(request);
	// try {
	// String downloadfFileName = request.getParameter("filename");
	// downloadfFileName = new String(
	// downloadfFileName.getBytes("iso-8859-1"), "utf-8");
	// String fileName = downloadfFileName.substring(downloadfFileName
	// .indexOf("_") + 1);
	// String userAgent = request.getHeader("User-Agent");
	// byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
	// : fileName.getBytes("UTF-8");
	// fileName = new String(bytes, "ISO-8859-1");
	// response.setHeader("Content-disposition",
	// String.format("attachment; filename=\"%s\"", fileName));
	// FileOperateUtil.download(downloadfFileName,
	// response.getOutputStream());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	private void init(HttpServletRequest request) {
		if (FileOperateUtil.FILEDIR == null) {
			FileOperateUtil.FILEDIR = request.getSession().getServletContext()
					.getRealPath("/")
					+ "file/";
		}
	}

}
