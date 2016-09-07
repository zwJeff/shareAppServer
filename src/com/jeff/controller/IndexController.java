package com.jeff.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import mUtils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.respModel.ResourceRespModel;

@Controller
public class IndexController  extends BaseController{

	private ResponseToApp mRespopnseData = null;

	private ResourceModel resource = null;


	/**
	 * ???????
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_news_pictures_list.do")
	public void getNewsPicturesList(String token, HttpServletResponse resp) {
		if (-1==userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token???", 0, "");
		} else {

			List<PictureListModel> pictureList = indexService.getPictureNewsList();
			if (pictureList == null) {
				mRespopnseData = new ResponseToApp("400", "?????????", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success",
						pictureList.size(), pictureList);
			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * ??????
	 *
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_index_course.do")
	public void getIndexCourse(String token, HttpServletResponse resp) {

		if (-1==userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token???", 0, "");
		} else {

			List<CourseTypeModel> courseTypes = indexService.getCourse(1);
			if (courseTypes == null) {
				mRespopnseData = new ResponseToApp("400", "???????", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success",
						courseTypes.size(), courseTypes);
			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ????????
	 *
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_all_course.do")
	public void getAllCourse(String token, HttpServletResponse resp) {

		if (-1==userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token???", 0, "");
		} else {

			List<CourseTypeModel> courseTypes = indexService.getCourse(0);
			if (courseTypes == null) {
				mRespopnseData = new ResponseToApp("400", "???????", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success",
						courseTypes.size(), courseTypes);
			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ??????
	 *
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_hot_resource.do")
	public void getHotResource(String token, HttpServletResponse resp) {

		if (-1==userinfoService.isTokenValid(token)) {
			mRespopnseData = new ResponseToApp("440", "token???", 0, "");
		} else {

			List<ResourceRespModel> hotResources = resourceService.getHotResource();
			if (hotResources == null) {
				mRespopnseData = new ResponseToApp("400", "?????????", 0, "");
			} else {
				mRespopnseData = new ResponseToApp("200", "success",
						hotResources.size(), hotResources);
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
