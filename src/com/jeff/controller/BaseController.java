package com.jeff.controller;

import javax.annotation.Resource;

import com.jeff.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeff.model.ResponseToApp;

public class BaseController {
	
	protected ResponseToApp mRespopnseData;
	
	@Resource(name="UserinfoService")
	protected  UserinfoService userinfoService;

	@Resource(name="ResourceService")
    protected  ResourceService resourceService;

	@Resource(name="CollectListService")
	protected  CollectListService collectListService;
	
	@Resource(name="IndexService")
	protected  IndexService indexService;

	@Resource(name="TaskService")
	protected  TaskService taskService;


	@Resource(name="AdsService")
	protected AdsService adsService;
	
	
	
}
