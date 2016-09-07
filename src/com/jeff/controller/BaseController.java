package com.jeff.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeff.model.ResponseToApp;
import com.jeff.service.CollectListService;
import com.jeff.service.IndexService;
import com.jeff.service.ResourceService;
import com.jeff.service.TaskService;
import com.jeff.service.UserinfoService;

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
	
	
	
}
