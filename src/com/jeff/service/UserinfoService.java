package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.model.UserinfoModel;

public interface UserinfoService {
	
	/**
	 * �ж�token�Ƿ����
	 * @param token
	 * @return token���ڷ���-1��δ���ڷ����û�id
	 */
	public int isTokenValid(String token);
	
	
	/**
	 * �Զ���½
	 * @param token
	 * @return
	 */
	UserinfoModel auto_login(String token);
	
	/**
	 * ��½
	 * @param token
	 * @return
	 */
	public UserinfoModel login(String loginname,String password,String token);
	
	/**
	 * ע��
	 * @param userinfo
	 * @return
	 */
	String register(UserinfoModel userinfo);

	/**
	 * ��ȡ�û�����
	 * @param token
	 * @param user_id
	 * @return
	 */
	UserinfoModel getUserinfo(String user_id);


	/**
	 * �޸�ͷ��
	 * @param user_id
	 * @param user_picture_url
	 * @return
	 */
	public int changeHeadImg(String user_id, String user_picture_url);


	/**
	 * �󶨽������
	 * @param user_id
	 * @param email
	 * @return
	 */
	public int bindEmail(String user_id, String email);
	
	/**
	 * �󶨽���ֻ���
	 * @param user_id
	 * @param email
	 * @return
	 */
	public int bindTelephone(String user_id, String telephone);


	/**
	 * ע����½
	 * @param user_id
	 */
	public void logout(String user_id);




	/**
	 * �޸�����ǰ�û����ֻ���У��ӿ� 
	 * @param username
	 * @param phone
	 * @return
	 */
	public String CheckNamePhone(String username, String phone);

	/**
	 * �޸�����ӿ�
	 * @param user_id
	 * @param new_password
	 */
	public String changePassword(String user_id, String new_password);
	
}
