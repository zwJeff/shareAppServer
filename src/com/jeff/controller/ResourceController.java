package com.jeff.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mUtils.ResponseUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.ResourceService;
import com.jeff.service.UserinfoService;
import com.jeff.serviceImp.UserinfoServiceImp;

@Controller
public class ResourceController extends BaseController {

	private ResponseToApp mRespopnseData = null;

	private ResourceModel resource = null;

	@RequestMapping(value = "/get_resource_info.do")
	public void getResourceinfo(String token, String resource_id,
			HttpServletResponse resp) {
		if (-1 == userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			ResourceRespModel result = resourceService
					.getResourceInfo(resource_id);
			if (result == null) {
				mRespopnseData = new ResponseToApp("400", "获取资源详情出错！", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success", 1,
						result);
			}
		}
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/collect.do")
	public void collect(String token, int resource_id,
			HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			String result = resourceService.collect(resource_id, user_id);

			mRespopnseData = new ResponseToApp("200", result, 0, null);

		}
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/download.do")
	public void download(String token, String user_id, String resource_id,
			HttpServletResponse resp) {
		if (-1 == userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			String status = "";// resourceService.download(user_id,
								// resource_id);
			switch (status) {

			case "1":
				mRespopnseData = new ResponseToApp("404", "资源不存在！", 0, "");
				break;

			case "2":
				mRespopnseData = new ResponseToApp("405", "资源附件不存在！", 0, "");
				break;

			case "3":
				mRespopnseData = new ResponseToApp("404", "当前用户不存在", 0, "");
				break;

			case "4":
				mRespopnseData = new ResponseToApp("407", "添加下载记录出错！", 0, "");
				break;
			default: {
				HashMap<String, String> mData = new HashMap<String, String>();
				mData.put("resource_url", status);
				mRespopnseData = new ResponseToApp("200", "success", 1, mData);
			}

			}

		}
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/upload_resource.do")
	public void upload(String token, String resource_name,
			String resource_url, String resource_describe,
			String resource_course_type, int resource_file_type,
			HttpServletResponse resp) {
		int user_id=userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			String status = resourceService.upload(user_id+"", resource_name,
					resource_url, resource_describe, resource_course_type,
					resource_file_type);

			switch (status) {
			case "1":
				mRespopnseData = new ResponseToApp("404", "当前用户不存在！", 0, "");
				break;

			case "2":
				mRespopnseData = new ResponseToApp("405", "资源信息插入失败！", 0, "");
				break;

			case "3":
				mRespopnseData = new ResponseToApp("406", "课程类型不存在！", 0, "");
				break;

			default:
				mRespopnseData = new ResponseToApp("200", "success", 1, status);

			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/get_resource_url.do")
	public void getResourceUrl(String token, String resource_id,
			HttpServletResponse resp) {
		if (-1 == userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			String status = resourceService.getResourceUrl(resource_id);

			if (status == null) {
				mRespopnseData = new ResponseToApp("404", "资源链接不存在！", 0, "");
			} else {
				HashMap<String, String> mData = new HashMap<String, String>();
				mData.put("resource_url", status);
				mRespopnseData = new ResponseToApp("200", "success", 1, mData);
			}

		}
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
