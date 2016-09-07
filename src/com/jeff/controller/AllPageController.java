package com.jeff.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mUtils.ResponseUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeff.dao.CourseTypeDao;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.ResourceService;
import com.jeff.service.UserinfoService;
import com.jeff.serviceImp.UserinfoServiceImp;

@Controller
public class AllPageController extends BaseController {

	private ResponseToApp mRespopnseData = null;

	private ResourceModel resource = null;

	/**
	 * 获取所有课程接口
	 * 
	 * @param token
	 * @param resp
	 */
//	@RequestMapping(value = "/get_all_course.do")
//	public void getAllCourse(String token, HttpServletResponse resp) {
//		if (-1 == userinfoService.isTokenValid(token)) {
//			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
//		} else {
//
//			List<CourseTypeModel> courseTypes = indexService.getAllCourse();
//			if (courseTypes == null) {
//				mRespopnseData = new ResponseToApp("400", "获取热门资源出错！", 0, "");
//			} else {
//				mRespopnseData = new ResponseToApp("200", "success",
//						courseTypes.size(), courseTypes);
//			}
//		}
//
//		try {
//			ResponseUtils.resposeToApp(resp, mRespopnseData);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	/**
	 * 获取资源
	 * @param token
	 * @param resource_course_type
	 * @param resource_file_type
	 * @param sort_type
	 * @param page_count
	 * @param page_number
	 * @param resp
	 */
	@RequestMapping(value = "/get_resource_list.do")
	public void getResourceList(String token, int resource_course_type,
			int resource_file_type, int sort_type, int page_count,
			int page_number, HttpServletResponse resp) {
		int user_id=userinfoService.isTokenValid(token);
		if (-1==user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			List<ResourceRespModel> resourcesList = resourceService
					.getResourceList(resource_course_type,sort_type, resource_file_type, page_count, page_number, user_id);
			if (resourcesList == null) {
				mRespopnseData = new ResponseToApp("400", "获取资源出错！", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success",
						resourcesList.size(), resourcesList);
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
