package com.jeff.serviceImp;

import com.jeff.model.AdsStatusModel;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.AdsService;
import com.jeff.service.CollectListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("AdsService")
public class AdsServiceImp extends BaseService implements AdsService{
	
	public List<AdsStatusModel> getAdsStatus() {
		// TODO Auto-generated method stub
		String hql = "from AdsStatusModel p ";
		List<AdsStatusModel> result = new ArrayList<AdsStatusModel>();
		result = adsInfoDao.findList(hql);
		return result;

	}
}
