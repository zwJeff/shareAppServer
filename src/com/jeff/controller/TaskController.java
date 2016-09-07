package com.jeff.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mUtils.MUtils;
import mUtils.ResponseUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeff.dao.CourseTypeDao;
import com.jeff.model.AnswerCardModel;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;
import com.jeff.model.QuestionModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.ResponseToApp;
import com.jeff.model.TaskModel;
import com.jeff.model.TestPaperModel;
import com.jeff.model.UserinfoModel;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.respModel.TaskRespModel;
import com.jeff.respModel.TeacherTaskDetialModel;
import com.jeff.service.ResourceService;
import com.jeff.service.UserinfoService;
import com.jeff.serviceImp.UserinfoServiceImp;

@Controller
public class TaskController extends BaseController {

	private ResponseToApp mRespopnseData = null;

	private TaskModel task = null;

	/**
	 * 获取学生当前/历史任务列表接口
	 * 
	 * @param token
	 * @param resp
	 */
	@RequestMapping(value = "/get_student_task_list.do")
	public void getStudentTaskList(String token, int task_statu,int page_num,int page_count,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			List<TaskRespModel> tasks = taskService.getStudentTaskList(user_id,
					task_statu,page_num,page_count);
			if (tasks == null)
				mRespopnseData = new ResponseToApp("200", "success", 0, tasks);
			else
				mRespopnseData = new ResponseToApp("200", "success",
						tasks.size(), tasks);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/get_student_task_detial.do")
	public void getStudentTaskDetial(String token, int task_id,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			TaskRespModel task = taskService.getStudentTaskDetial(user_id,
					task_id);

			mRespopnseData = new ResponseToApp("200", "success", 1, task);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/get_student_task_new.do")
	public void getStudentTaskNew(String token,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			int newTaskNum = taskService.getStudentTaskNew(user_id);

			mRespopnseData = new ResponseToApp("200", "success", newTaskNum, newTaskNum);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/get_task_test.do")
	public void getTaskTest(String token, int task_id, HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			List<QuestionModel> qs = taskService.getTaskTest(task_id);

			if (qs == null)
				mRespopnseData = new ResponseToApp("200", "success", 0, qs);
			else
				mRespopnseData = new ResponseToApp("200", "success", qs.size(),
						qs);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/submit_test.do")
	public void submitTest(String token, int task_id, String question0_answer,
			int question0_score, String question1_answer, int question1_score,
			String question2_answer, int question2_score,
			String question3_answer, int question3_score,
			String question4_answer, int question4_score,
			String question5_answer, int question5_score,
			String question6_answer, int question6_score,
			String question7_answer, int question7_score,
			String question8_answer, int question8_score,
			String question9_answer, int question9_score, int total_score,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			AnswerCardModel answer = new AnswerCardModel(task_id, 2,
					MUtils.getCurrentSqlDate(), user_id, question0_answer,
					question0_score, question1_answer, question1_score,
					question2_answer, question2_score, question3_answer,
					question3_score, question4_answer, question4_score,
					question5_answer, question5_score, question6_answer,
					question6_score, question7_answer, question7_score,
					question8_answer, question8_score, question9_answer,
					question9_score, total_score);

			String result = taskService.submitTest(answer);

			mRespopnseData = new ResponseToApp("200", result, 0, null);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/add_question.do")
	public void addQuestion(String token, String question_description,
			int description_type, String answer_a, String answer_b,
			String answer_c, String answer_d, String answer_text,
			String true_answer, int total_score, HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			QuestionModel q = new QuestionModel(question_description,
					description_type, answer_a, answer_b, answer_c, answer_d,
					answer_text, true_answer, total_score);

			int question_id = taskService.addQuestion(q);

			if (question_id == -1)
				mRespopnseData = new ResponseToApp("400", "题目新建失败！", 0, null);
			else
				mRespopnseData = new ResponseToApp("200", "success", 1,
						question_id);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/arrangement_work.do")
	public void arrangementWork(String token, int question0_id,
			int question1_id, int question2_id, int question3_id,
			int question4_id, int question5_id, int question6_id,
			int question7_id, int question8_id, int question9_id,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			TestPaperModel testPaper = new TestPaperModel(question0_id,
					question1_id, question2_id, question3_id, question4_id,
					question5_id, question6_id, question7_id, question8_id,
					question9_id);

			int test_paper_id = taskService.arrangementWork(testPaper);

			if (test_paper_id == -1)
				mRespopnseData = new ResponseToApp("400", "数据存储出错", 0, null);
			else
				mRespopnseData = new ResponseToApp("200", "success", 1,
						test_paper_id);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/add_task.do")
	public void addTask(String token, int resource_id, String task_name,
			int paper_id, HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			TaskModel task = new TaskModel();
			task.setNewTime(MUtils.getCurrentSqlDate());
			task.setPaperId(paper_id);
			task.setResourceId(resource_id);
			task.setTeacherId(user_id);
			task.setTaskName(task_name);

			int task_id = taskService.addTask(task);

			if (task_id == -1)
				mRespopnseData = new ResponseToApp("400", "新建任务失败！", 0, null);
			else {
				mRespopnseData = new ResponseToApp("200", "success", 1, task_id);
			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/find_student.do")
	public void findSudent(String token, String key, HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			List<UserinfoModel> users = taskService.findStudent(key);
			if (users == null)
				mRespopnseData = new ResponseToApp("200", "success", 0, users);
			else
				mRespopnseData = new ResponseToApp("200", "success",
						users.size(), users);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/add_student.do")
	public void addStudent(String token, int student_id, int task_id,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			int result = taskService.addStudent(student_id, task_id);

			if (result == -1)
				mRespopnseData = new ResponseToApp("400", "添加失败！", 0, null);
			else {
				mRespopnseData = new ResponseToApp("200", "success", 0, null);
			}
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/get_teacher_task_list.do")
	public void getTeacherTaskList(String token, int task_statu,int page_count,int page_num,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			List<TaskRespModel> tasks = taskService.getTeacherTaskList(user_id,
					task_statu, page_count, page_num);
			if (tasks == null)
				mRespopnseData = new ResponseToApp("200", "success", 0, tasks);
			else
				mRespopnseData = new ResponseToApp("200", "success",
						tasks.size(), tasks);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/get_teacher_task_detial.do")
	public void getTeacherTaskDetial(String token, int task_id,
			HttpServletResponse resp) {

		int user_id = userinfoService.isTokenValid(token);
		if (-1 == user_id) {
			mRespopnseData = new ResponseToApp("440", "token过期！", 0, "");
		} else {

			List<TeacherTaskDetialModel> result = taskService
					.getTeacherTaskDetial(task_id);
			if (result == null)
				mRespopnseData = new ResponseToApp("200", "success", 0, result);
			else
				mRespopnseData = new ResponseToApp("200", "success",
						result.size(), result);
		}

		try {
			ResponseUtils.resposeToApp(resp, mRespopnseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
