package com.jeff.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import mUtils.MUtils;
import mUtils.ResponseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.model.UserinfoModel;
import com.jeff.service.CollectListService;
import com.jeff.service.UserinfoService;
import com.jeff.serviceImp.UserinfoServiceImp;

@Controller
public class UserinfoController extends BaseController {

	private UserinfoModel userinfo;

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param telephone
	 */
	@RequestMapping(value = "/register.do")
	public void register(String username, String user_type, String password,
			String email, String telephone,
			HttpServletResponse resp) {

		System.out.println(UserinfoController.class.getName());
		System.out.println("-----controll层------");
		String token=MUtils.creatToken();
		userinfo = new UserinfoModel(username, Integer.parseInt(user_type),
				password, telephone, email, token);

		String result = userinfoService.register(userinfo);

		if (MUtils.isNumber(result)) {
			HashMap<String, UserinfoModel> mData = new HashMap<String, UserinfoModel>();
			mData.put("user", userinfo);
			mRespopnseData.setResult(mData);
			mRespopnseData = new ResponseToApp("200", "注册成功！", 1, mData);
		} else {
			mRespopnseData = new ResponseToApp("400", result, 0, "");
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 验证码
	 * 
	 * @param telephone_or_email
	 * @param verification_code
	 */
	@RequestMapping(value = "/get_verification_code.do")
	public void getVerificationCode(String telephone_or_email,
			String verification_code) {

		if (MUtils.isTelephone(telephone_or_email)) {
			System.out.println("【shareApp】您的手机号验证码为 " + verification_code);
			System.out.println("【shareApp】您的手机号验证码为 " + verification_code);
			System.out.println("【shareApp】您的手机号验证码为 " + verification_code);
		} else if (MUtils.isEmail(telephone_or_email)) {
			System.out.println("【shareApp】您的邮箱号验证码为 " + verification_code);
			System.out.println("【shareApp】您的邮箱号验证码为 " + verification_code);
			System.out.println("【shareApp】您的邮箱号验证码为 " + verification_code);
		}

	}

	/**
	 * 快速登录
	 */
	@RequestMapping(value = "/auto_login.do")
	public void auto_login(String token, HttpServletResponse resp) {

		System.out.println(UserinfoController.class.getName());
		System.out.println("-----controll层------");
		UserinfoModel user = userinfoService.auto_login(token);
		if (user == null) {
			mRespopnseData = new ResponseToApp("440", "token已过期！", 0, "");
		} else {
			HashMap<String, Integer> mData = new HashMap<String, Integer>();
			mRespopnseData = new ResponseToApp("200", "登录成功！", 1, user);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 登录
	 *
	 */
	@RequestMapping(value = "/login.do")
	public void login(String loginname, String password,
			HttpServletResponse resp) {

		System.out.println(UserinfoController.class.getName());
		System.out.println("-----controll层------loginname=" + loginname
				+ "password=" + password);

        String token=MUtils.creatToken();

		UserinfoModel result = userinfoService
				.login(loginname, password, token);
		if (result == null) {
			mRespopnseData = new ResponseToApp("400", "账号或密码错误！", 0, "");
		} else {
            result.setToken(token);
			mRespopnseData = new ResponseToApp("200", "success", 1, result);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改密码前用户名手机号校验接口
	 * 
	 * @param username
	 */
	@RequestMapping(value = "/check_name_phone.do")
	public void CheckNamePhone(String username, String telephone,
			HttpServletResponse resp) {
		String message = userinfoService.CheckNamePhone(username, telephone);
		if (message.equals("-1"))
			mRespopnseData = new ResponseToApp("400", "手机号不存在！！", 1, null);
		else if (message.equals("-2"))
			mRespopnseData = new ResponseToApp("400", "用户名手机号不匹配！", 1, null);
		else
			mRespopnseData = new ResponseToApp("200", "success", 1, message);
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改密码接口
	 *
	 */
	@RequestMapping(value = "/change_password.do")
	public void changePassword(String token, String new_password,
			HttpServletResponse resp) {
		int userId = userinfoService.isTokenValid(token);

		if (-1 == userId) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {
			String message = userinfoService.changePassword(userId+"",
					new_password);
			mRespopnseData = new ResponseToApp("200", message, 0, null);
		}
		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
