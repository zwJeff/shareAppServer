package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.respModel.ResourceRespModel;

public interface CollectListService {
	
	/**
	 * ��ȡ�ҵ��ղ��б�
	 * @param user_id
	 * @return
	 */
	public List<ResourceRespModel> getCollectList(String user_id);

}
