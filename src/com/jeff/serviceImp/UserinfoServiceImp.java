package com.jeff.serviceImp;

import java.util.ArrayList;
import java.util.List;

import mUtils.MUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeff.dao.UserinfoDao;
import com.jeff.model.CollectListModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.UserinfoModel;
import com.jeff.service.UserinfoService;

@Service("UserinfoService")
public class UserinfoServiceImp extends BaseService implements UserinfoService {

	private UserinfoModel user;

	public int isTokenValid(String token) {
		String hql = "from UserinfoModel u where u.token= '" + token + "'";
		List<UserinfoModel> users = userinfoDao.findList(hql);
		if (users == null||users.size()<=0)
			return -1;
		else
			return users.get(0).getUserId();
	}

	public String register(UserinfoModel userinfo) {
		// TODO Auto-generated method stub

		String hql;

		String status;
		// 查重
		if (userinfo.getEmail() == null
				|| userinfo.getEmail().trim().equals("")) {
			if (userinfo.getTelephone() != null
					&& !userinfo.getTelephone().trim().equals("")) {
				hql = "from UserinfoModel u where u.telephone= '"
						+ userinfo.getTelephone() + "'";
				status = "电话号码已注册！";
			} else {
				return "手机号或邮箱为空！";
			}
		} else {
			hql = "from UserinfoModel u where u.email= '" + userinfo.getEmail()
					+ "'";
			status = "邮箱已注册！";
		}

		user = userinfoDao.find(hql);
		if (user != null) {
			return status;
		}
		// 插入用户信息
		int result = userinfoDao.insert(userinfo);
		if (result >= 0)
			return result + "";
		else
			return "数据库插入出错!";
	}

	/**
	 * 快速登录
	 * 
	 * @param userinfo
	 * @return
	 */
	public UserinfoModel auto_login(String token) {
		// TODO Auto-generated method stub

		String hql = "from UserinfoModel u where u.token='" + token + "'";

		user = userinfoDao.find(hql);

		if (user == null) {
			return null;
		}
		user.setPassword(null);
		user.setToken(null);
		return user;
	}

	/**
	 * 登录
	 * 
	 * @param userinfo
	 * @return
	 */
	public UserinfoModel login(String loginname, String password, String token) {
		// TODO Auto-generated method stub

		String hql;
		if (MUtils.isTelephone(loginname))
			hql = "from UserinfoModel u where u.telephone= '" + loginname
					+ "' and u.password='" + password + "'";
		else if (MUtils.isEmail(loginname))
			hql = "from UserinfoModel u where u.email= '" + loginname
					+ "' and u.password='" + password + "'";
		else
			hql = "from UserinfoModel u where u.username= '" + loginname
					+ "' and u.password='" + password + "'";
		user = userinfoDao.find(hql);
		if (user == null) {
			return null;
		}
		user.setToken(token);
		System.out.println("======now token is " + user.getToken());
		userinfoDao.update(user);
		user.setPassword(null);
		user.setToken(null);
		return user;
	}

	/**
	 * 修改密码前用户名手机号校验接口
	 * 
	 * @param username
	 * @param phone
	 * @return
	 */
	public String CheckNamePhone(String username, String phone) {
		String hql = "from UserinfoModel u where u.telephone= '" + phone + "'";
		user = userinfoDao.find(hql);
		if (user == null)
			return "-1";
		else if (!username.equals(user.getUsername()))
			return "-2";
		else
			return user.getPassword();
	}

	/**
	 * 修改密码接口
	 * 
	 * @param user_id
	 * @param new_password
	 */
	public String changePassword(String user_id, String new_password) {

		String hql = "from UserinfoModel u where u.userId= " + user_id;
		user = userinfoDao.find(hql);
		if (user == null)
			return "修改失败，账户不存在！";
		user.setPassword(new_password);
		userinfoDao.update(user);
		return "修改成功，请重新登录！";
	}

	/**
	 * 获取用户资料
	 */
	public UserinfoModel getUserinfo(String user_id) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel u where u.userId= '" + user_id + "'";
		user = userinfoDao.find(hql);
		return user;
	}

	/**
	 * 修改头像
	 */
	public int changeHeadImg(String user_id, String user_picture_url) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel u where u.userId= '" + user_id + "'";

		user = userinfoDao.find(hql);
		if (user == null) {
			return -2;
		}

		user.setUserHeadUrl(user_picture_url);

		return userinfoDao.update(user);
	}

	/**
	 * 绑定解绑邮箱
	 */
	public int bindEmail(String user_id, String email) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel u where u.email= '" + email + "'";
		user = userinfoDao.find(hql);
		// 邮箱有重复
		if (user != null) {
			return -3;
		}

		hql = "from UserinfoModel u where u.userId= '" + user_id + "'";
		user = userinfoDao.find(hql);
		// 用户不存在
		if (user == null) {
			return -2;
		}

		// 邮箱和手机号不能都取消绑定
		if (user.getTelephone() == null && email == null)
			return -4;

		user.setEmail(email);
		return userinfoDao.update(user);
	}

	/**
	 * 绑定解绑手机号
	 */
	public int bindTelephone(String user_id, String telephone) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel u where u.telephone= '" + telephone
				+ "'";
		user = userinfoDao.find(hql);
		if (user != null) {
			return -3;
		}

		hql = "from UserinfoModel u where u.userId= '" + user_id + "'";
		user = userinfoDao.find(hql);
		if (user == null) {
			return -2;
		}

		// 邮箱和手机号不能都取消绑定
		if (user.getEmail() == null && telephone == null)
			return -4;

		user.setTelephone(telephone);

		return userinfoDao.update(user);
	}

	/**
	 * 注销登陆
	 */
	public void logout(String user_id) {
		// TODO Auto-generated method stub

		String hql = "from UserinfoModel u where u.userId=" + user_id;
		user = userinfoDao.find(hql);
		if (user != null) {
			user.setToken(null);
			userinfoDao.update(user);
		}
	}

}
