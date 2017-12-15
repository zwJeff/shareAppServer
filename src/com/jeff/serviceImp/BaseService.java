package com.jeff.serviceImp;

import javax.annotation.Resource;

import com.jeff.dao.*;

public class BaseService {
	
	@Resource(name="UserinfoDao") 
	protected UserinfoDao userinfoDao;
	
	@Resource(name="CollectListDao")
	protected CollectListDao collectListDao;
	
	@Resource(name="ResourceDao")
	protected ResourceDao resourceDao;
	
	@Resource(name="CourseTypeDao")
	protected CourseTypeDao courseTypeDao;
	
	@Resource(name="AnswerCardDao")
	protected AnswerCardDao answerCardDao;
	
	@Resource(name="PictureListDao")
	protected PictureListDao pictureListDao;
	
	@Resource(name="QuestionDao")
	protected QuestionDao questionDao;
	
	@Resource(name="TaskDao")
	protected TaskDao taskDao;

	@Resource(name="TestPaperDao")
	protected TestPaperDao testPaperDao;

	@Resource(name="AdsInfoDao")
	protected AdsInfoDao adsInfoDao;




}
