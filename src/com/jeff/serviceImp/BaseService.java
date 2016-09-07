package com.jeff.serviceImp;

import javax.annotation.Resource;

import com.jeff.dao.AnswerCardDao;
import com.jeff.dao.CollectListDao;
import com.jeff.dao.CourseTypeDao;
import com.jeff.dao.PictureListDao;
import com.jeff.dao.QuestionDao;
import com.jeff.dao.ResourceDao;
import com.jeff.dao.TaskDao;
import com.jeff.dao.TestPaperDao;
import com.jeff.dao.UserinfoDao;

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
	

	
	
}
