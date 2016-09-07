package com.jeff.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mUtils.ResponseUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.model.UserinfoModel;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.CollectListService;

@Controller
public class MyPageController extends BaseController {

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_userinfo.do")
	public void getUserinfo(String token, HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		}
		UserinfoModel userinfo = userinfoService.getUserinfo(user_id + "");
		if (userinfo == null) {
			mRespopnseData = new ResponseToApp("400", "id不存在！", 0, "");
		} else {
			userinfo.setPassword(null);
			userinfo.setToken(null);
			mRespopnseData = new ResponseToApp("200", "success", 1, userinfo);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改头像
	 * 
	 * @param token
	 * @param user_picture_url
	 */
	@RequestMapping(value = "/change_headImg.do")
	public void changeHeadImg(String token, String user_picture_url,
			HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			int result = userinfoService.changeHeadImg(user_id + "",
					user_picture_url);
			switch (result) {
			case -2:
				mRespopnseData = new ResponseToApp("404", "用户id不存在！", 0, "");
				break;
			case -1:
				mRespopnseData = new ResponseToApp("410", "数据库操作出错！", 0, "");
				break;
			default:
				mRespopnseData = new ResponseToApp("200", "success", 0, "");
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
	 * 绑定解绑邮箱
	 * 
	 * @param token
	 * @param email
	 */
	@RequestMapping(value = "/bind_email.do")
	public void bindEmail(String token, String email, HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			int result = userinfoService.bindEmail(user_id + "", email);
			switch (result) {
			case -4:
				mRespopnseData = new ResponseToApp("430", "邮箱和手机号至少需要绑定一项！", 0,
						"");
				break;
			case -3:
				mRespopnseData = new ResponseToApp("420", "email已存在！", 0, "");
				break;
			case -2:
				mRespopnseData = new ResponseToApp("404", "用户id不存在！", 0, "");
				break;
			case -1:
				mRespopnseData = new ResponseToApp("410", "数据库操作出错！", 0, "");
				break;
			default:
				mRespopnseData = new ResponseToApp("200", "success", 0, "");
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
	 * 绑定解绑手机号
	 * 
	 * @param token
	 * @param user_id
	 * @param telephone
	 */
	@RequestMapping(value = "/bind_telephone.do")
	public void bindTelephone(String token, String user_id, String telephone,
			HttpServletResponse resp) {

		if (userinfoService.isTokenValid(token) == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			int result = userinfoService.bindTelephone(user_id, telephone);
			switch (result) {
			case -4:
				mRespopnseData = new ResponseToApp("430", "邮箱和手机号至少需要绑定一项！", 0,
						"");
				break;
			case -3:
				mRespopnseData = new ResponseToApp("420", "手机号码已存在！", 0, "");
				break;
			case -2:
				mRespopnseData = new ResponseToApp("404", "用户id不存在！", 0, "");
				break;
			case -1:
				mRespopnseData = new ResponseToApp("410", "数据库操作出错！", 0, "");
				break;
			default:
				mRespopnseData = new ResponseToApp("200", "success", 0, "");
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
	 * 注销登陆
	 *
	 */
	@RequestMapping(value = "/logout.do")
	public void logout(String token,HttpServletResponse resp) {
		int user_id=userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} 
		userinfoService.logout(user_id+"");
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取分享列表
	 * 
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_upload_list.do")
	public void getUploadList(String token, HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			List<ResourceRespModel> list = resourceService
					.getUploadList(user_id + "");
			if (list != null)
				mRespopnseData = new ResponseToApp("200", "success",
						list.size(), list);
			else
				mRespopnseData = new ResponseToApp("200", "success", 0, list);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取收藏列表
	 * 
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_collect_list.do")
	public void getCollectList(String token, HttpServletResponse resp) {
		int user_id = userinfoService.isTokenValid(token);
		if (user_id == -1) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			List<ResourceRespModel> list = collectListService
					.getCollectList(user_id + "");
			if (list != null)
				mRespopnseData = new ResponseToApp("200", "success",
						list.size(), list);
			else
				mRespopnseData = new ResponseToApp("200", "success", 0, list);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
