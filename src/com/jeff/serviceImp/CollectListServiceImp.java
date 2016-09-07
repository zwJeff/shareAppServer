package com.jeff.serviceImp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeff.dao.CollectListDao;
import com.jeff.model.ResourceModel;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.CollectListService;

@Service("CollectListService")
public class CollectListServiceImp extends BaseService implements CollectListService{
	
	public List<ResourceRespModel> getCollectList(String user_id) {
		// TODO Auto-generated method stub
		String hql = " select new map(r.resourceId as resourceId, r.resourceName as resourceName, r.resourceFileType as resourceFileType,"
				+ " r.resourceAuthor as resourceAuthor) "
				+ "from ResourceModel r, CollectListModel s where r.resourceId=s.collectRecourceId and s.collectUserId='"
				+ user_id + "'";

		List<ResourceRespModel> result = new ArrayList<ResourceRespModel>();

		List<HashMap> list = collectListDao.findTables(hql);
		for (HashMap m : list) {
			result.add(new ResourceRespModel((int) m.get("resourceId"),
					(String) m.get("resourceAuthor"), (String) m
							.get("resourceName"), (int) m
							.get("resourceFileType")));
		}
		return result;

	}
}
