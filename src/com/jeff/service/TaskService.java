package com.jeff.service;

import java.util.List;

import com.jeff.dao.QuestionDao;
import com.jeff.model.AnswerCardModel;
import com.jeff.model.QuestionModel;
import com.jeff.model.TaskModel;
import com.jeff.model.TestPaperModel;
import com.jeff.model.UserinfoModel;
import com.jeff.respModel.TaskRespModel;
import com.jeff.respModel.TeacherTaskDetialModel;


public interface TaskService {
	
	/**
	 * 获取当前/历史任务列表接口
	 * @param statu
	 * @param page_count 
	 * @param page_num 
	 * @return
	 */
	List<TaskRespModel> getStudentTaskList(int user_id,int statu, int page_num, int page_count);
	
	/**
	 * 获取任务详情接口
	 * @param task_id
	 * @return
	 */
	TaskRespModel getStudentTaskDetial(int user_id,int task_id);
	
	/**
	 * 获取课程作业接口
	 * @param test_paper_id
	 * @return
	 */
	List<QuestionModel> getTaskTest(int test_paper_id);
	
	/**
	 * 提交作业
	 * @param answer
	 * @return
	 */
	String submitTest(AnswerCardModel answer);
	
	/**
	 * 新建习题
	 * @param q
	 * @return
	 */
	int addQuestion(QuestionModel q);
	
	/**
	 * 新建任务
	 * @param t
	 * @return
	 */
	int addTask(TaskModel t);
	
	/**
	 * 老师布置课程作业接口
	 * @param q
	 * @return
	 */
	int arrangementWork(TestPaperModel test);
	
	/**
	 * 检索学生信息接口
	 * @param key
	 * @return
	 */
	List<UserinfoModel> findStudent(String key);
	
	/**
	 * 老师添加学习学生接口
	 * @param student_id
	 * @param task_ids
	 * @return
	 */
	int addStudent(int student_id,int task_ids);
	
	/**
	 * 老师获取当前/历史任务接口
	 * @param teacher_id
	 * @param task_statu
	 * @param page_num 
	 * @param page_count 
	 * @return
	 */
	List<TaskRespModel>getTeacherTaskList(int teacher_id,int task_statu, int page_count, int page_num);
	
	/**
	 *老师获取任务详情接口
	 * @return
	 */
	List<TeacherTaskDetialModel>getTeacherTaskDetial(int task_id);
	
	/**
	 * 获取作业答题结果
	 * @param user_id
	 * @param task_id
	 * @return
	 */
	AnswerCardModel  getAnswerCard(int user_id,int task_id);

	int getStudentTaskNew(int user_id);
}
