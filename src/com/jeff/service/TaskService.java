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
	 * ��ȡ��ǰ/��ʷ�����б�ӿ�
	 * @param statu
	 * @param page_count 
	 * @param page_num 
	 * @return
	 */
	List<TaskRespModel> getStudentTaskList(int user_id,int statu, int page_num, int page_count);
	
	/**
	 * ��ȡ��������ӿ�
	 * @param task_id
	 * @return
	 */
	TaskRespModel getStudentTaskDetial(int user_id,int task_id);
	
	/**
	 * ��ȡ�γ���ҵ�ӿ�
	 * @param test_paper_id
	 * @return
	 */
	List<QuestionModel> getTaskTest(int test_paper_id);
	
	/**
	 * �ύ��ҵ
	 * @param answer
	 * @return
	 */
	String submitTest(AnswerCardModel answer);
	
	/**
	 * �½�ϰ��
	 * @param q
	 * @return
	 */
	int addQuestion(QuestionModel q);
	
	/**
	 * �½�����
	 * @param t
	 * @return
	 */
	int addTask(TaskModel t);
	
	/**
	 * ��ʦ���ÿγ���ҵ�ӿ�
	 * @param q
	 * @return
	 */
	int arrangementWork(TestPaperModel test);
	
	/**
	 * ����ѧ����Ϣ�ӿ�
	 * @param key
	 * @return
	 */
	List<UserinfoModel> findStudent(String key);
	
	/**
	 * ��ʦ���ѧϰѧ���ӿ�
	 * @param student_id
	 * @param task_ids
	 * @return
	 */
	int addStudent(int student_id,int task_ids);
	
	/**
	 * ��ʦ��ȡ��ǰ/��ʷ����ӿ�
	 * @param teacher_id
	 * @param task_statu
	 * @param page_num 
	 * @param page_count 
	 * @return
	 */
	List<TaskRespModel>getTeacherTaskList(int teacher_id,int task_statu, int page_count, int page_num);
	
	/**
	 *��ʦ��ȡ��������ӿ�
	 * @return
	 */
	List<TeacherTaskDetialModel>getTeacherTaskDetial(int task_id);
	
	/**
	 * ��ȡ��ҵ������
	 * @param user_id
	 * @param task_id
	 * @return
	 */
	AnswerCardModel  getAnswerCard(int user_id,int task_id);

	int getStudentTaskNew(int user_id);
}
