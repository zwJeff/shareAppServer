package com.jeff.service;

import com.jeff.model.AdsStatusModel;
import com.jeff.respModel.ResourceRespModel;

import java.util.List;

public interface AdsService {
	
	/**
	 * @return
	 */
	List<AdsStatusModel> getAdsStatus();

}
