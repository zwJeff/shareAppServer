package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.model.UserinfoModel;

public interface UserinfoService {
	
	/**
	 * 判断token是否过期
	 * @param token
	 * @return token过期返回-1，未过期返回用户id
	 */
	public int isTokenValid(String token);
	
	
	/**
	 * 自动登陆
	 * @param token
	 * @return
	 */
	UserinfoModel auto_login(String token);
	
	/**
	 * 登陆
	 * @param token
	 * @return
	 */
	public UserinfoModel login(String loginname,String password,String token);
	
	/**
	 * 注册
	 * @param userinfo
	 * @return
	 */
	String register(UserinfoModel userinfo);

	/**
	 * 获取用户资料
	 * @param token
	 * @param user_id
	 * @return
	 */
	UserinfoModel getUserinfo(String user_id);


	/**
	 * 修改头像
	 * @param user_id
	 * @param user_picture_url
	 * @return
	 */
	public int changeHeadImg(String user_id, String user_picture_url);


	/**
	 * 绑定解绑邮箱
	 * @param user_id
	 * @param email
	 * @return
	 */
	public int bindEmail(String user_id, String email);
	
	/**
	 * 绑定解绑手机号
	 * @param user_id
	 * @param email
	 * @return
	 */
	public int bindTelephone(String user_id, String telephone);


	/**
	 * 注销登陆
	 * @param user_id
	 */
	public void logout(String user_id);




	/**
	 * 修改密码前用户名手机号校验接口 
	 * @param username
	 * @param phone
	 * @return
	 */
	public String CheckNamePhone(String username, String phone);

	/**
	 * 修改密码接口
	 * @param user_id
	 * @param new_password
	 */
	public String changePassword(String user_id, String new_password);
	
}
