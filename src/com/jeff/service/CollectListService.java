package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.respModel.ResourceRespModel;

public interface CollectListService {
	
	/**
	 * 获取我的收藏列表
	 * @param user_id
	 * @return
	 */
	public List<ResourceRespModel> getCollectList(String user_id);

}
