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
	// ��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�
	String path = "F://shareApp_data";
	String path2 = "F://shareApp_data/headPic/";
	String fileName;

	@RequestMapping(value = "upload.do")
	public void upload(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("uploading");

		request.setCharacterEncoding("utf-8");
		// ��ô����ļ���Ŀ������
		@SuppressWarnings("deprecation")
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// ������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�
		factory.setRepository(new File(path));
		// ���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�
		factory.setSizeThreshold(1024 * 1024);
		// �ϴ��������ࣨ��ˮƽAPI�ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// ���� parseRequest��request������ ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// ��ȡ���������֡�
				String name = item.getFieldName();
				System.out.println("uploading-getFiledname:" + name);

				// �����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ�����
					String value = item.getString();
					request.setAttribute(name, value);
				}
				// ���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���
				else {
					// ��ȡ·����
					String value = item.getName();
					System.out.println("uploading-getName:" + value);
					// ȡ�����һ����б�ܡ�
					int start = value.lastIndexOf("\\");
					// ��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�
					String filename = value.substring(start + 1);

					System.out.println("uploading-filename:" + filename);
					request.setAttribute(name, filename);

					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */

					System.out.println("uploading--filename:" + filename);
					path += addPrefix(filename) + "/";
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(path,
							filename));
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("��ȡ�ļ�����������:" + item.getSize());

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
		// ��ô����ļ���Ŀ������
		@SuppressWarnings("deprecation")
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// ������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�
		factory.setRepository(new File(path2));
		// ���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�
		factory.setSizeThreshold(1024 * 1024);
		// �ϴ��������ࣨ��ˮƽAPI�ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// ���� parseRequest��request������ ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// ��ȡ���������֡�
				String name = item.getFieldName();
				System.out.println("uploading:" + name);

				// �����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ�����
					String value = item.getString();
					request.setAttribute(name, value);
				}
				// ���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���
				else {
					// ��ȡ·����
					String value = item.getName();
					System.out.println("uploading:" + value);
					fileName = value;
					// ȡ�����һ����б�ܡ�
					int start = value.lastIndexOf("\\");
					// ��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�
					String filename = value.substring(start + 1);
					request.setAttribute(name, filename);

					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(path2,
							filename));
					InputStream in = item.getInputStream();

					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("��ȡ�ļ�����������:" + item.getSize());

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
