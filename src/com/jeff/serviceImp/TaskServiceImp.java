package com.jeff.serviceImp;

import java.util.ArrayList;
import java.util.List;

import mUtils.MUtils;

import org.springframework.stereotype.Service;

import com.jeff.dao.UserinfoDao;
import com.jeff.model.AnswerCardModel;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;
import com.jeff.model.QuestionModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.TaskModel;
import com.jeff.model.TestPaperModel;
import com.jeff.model.UserinfoModel;
import com.jeff.respModel.TaskRespModel;
import com.jeff.respModel.TeacherTaskDetialModel;
import com.jeff.service.IndexService;
import com.jeff.service.TaskService;

@Service("TaskService")
public class TaskServiceImp extends BaseService implements TaskService {

	private TaskModel taskModel;
	private TaskRespModel taskRespModel;

	@Override
	public List<TaskRespModel> getStudentTaskList(int user_id, int statu,
			int page_num, int page_count) {
		// TODO Auto-generated method stub
		String hql = "from AnswerCardModel a where a.answerUserId=" + user_id
				+ "and (a.taskStatu=";
		if (statu == 0) {
			hql += "0 or a.taskStatu=1)";
		} else {
			hql += "2)";
		}
		List<TaskRespModel> result = new ArrayList<TaskRespModel>();
		List<AnswerCardModel> answers = answerCardDao.findPageList(page_count,
				page_num, hql);
		if (answers == null)
			return null;
		for (AnswerCardModel a : answers) {
			hql = "from TaskModel t where t.taskId=" + a.getTaskId();
			TaskModel task = taskDao.find(hql);
			hql = "from UserinfoModel u where u.userId=" + task.getTeacherId();
			String authorName = ((UserinfoModel) userinfoDao.find(hql))
					.getUsername();
			taskRespModel = new TaskRespModel();
			taskRespModel.setTaskId(task.getTaskId());
			taskRespModel.setTaskName(task.getTaskName());
			taskRespModel.setTeacherName(authorName);
			taskRespModel.setTaskStatu(a.getTaskStatu());
			taskRespModel.setNewTime(task.getNewTime());
			result.add(taskRespModel);
		}
		return result;
	}

	@Override
	public TaskRespModel getStudentTaskDetial(int user_id, int task_id) {
		// TODO Auto-generated method stub
		AnswerCardModel temp = answerCardDao
				.find("from AnswerCardModel a where a.taskId=" + task_id
						+ " and a.answerUserId=" + user_id);
		temp.setTaskStatu(1);
		answerCardDao.update(temp);
		String hql = "from TaskModel a where a.taskId=" + task_id;
		taskModel = taskDao.find(hql);
		if (taskModel == null)
			return null;

		hql = "from UserinfoModel a where a.userId=" + taskModel.getTeacherId();
		UserinfoModel u = userinfoDao.find(hql);

		hql = "from ResourceModel a where a.resourceId="
				+ taskModel.getResourceId();
		ResourceModel r = resourceDao.find(hql);

		taskRespModel = new TaskRespModel();
		taskRespModel.setResourceId(taskModel.getResourceId());
		taskRespModel.setTaskName(taskModel.getTaskName());
		taskRespModel.setResourceName(r.getResourceName());
		taskRespModel.setResourceFileType(r.getResourceFileType());
		taskRespModel.setTeacherName(u.getUsername());
		taskRespModel.setNewTime(taskModel.getNewTime());

		return taskRespModel;

	}

	@Override
	public List<QuestionModel> getTaskTest(int test_paper_id) {
		// TODO Auto-generated method stub
		String hql = "from TestPaperModel t where t.paperId=" + test_paper_id;
		TestPaperModel testPaper = testPaperDao.find(hql);
		int[] questions = { testPaper.getQuestion0Id(),
				testPaper.getQuestion1Id(), testPaper.getQuestion2Id(),
				testPaper.getQuestion3Id(), testPaper.getQuestion4Id(),
				testPaper.getQuestion5Id(), testPaper.getQuestion6Id(),
				testPaper.getQuestion7Id(), testPaper.getQuestion8Id(),
				testPaper.getQuestion9Id() };

		List<QuestionModel> questionlist = new ArrayList<QuestionModel>();

		for (int i : questions) {
			hql = "from QuestionModel q where q.questionId=" + i;
			QuestionModel q = questionDao.find(hql);
			if (q != null)
				questionlist.add(q);
		}

		return questionlist;

	}

	@Override
	public String submitTest(AnswerCardModel answer) {
		// TODO Auto-generated method stub
		AnswerCardModel temp = answerCardDao
				.find("from AnswerCardModel a where a.taskId="
						+ answer.getTaskId() + " and a.answerUserId="
						+ answer.getAnswerUserId());
		if (temp.getFinishTime() == null)
			temp.setFinishTime(MUtils.getCurrentSqlDate());
		temp.setQuestion0Answer(answer.getQuestion0Answer());
		temp.setQuestion0Score(answer.getQuestion0Score());
		temp.setQuestion1Answer(answer.getQuestion1Answer());
		temp.setQuestion1Score(answer.getQuestion1Score());
		temp.setQuestion2Answer(answer.getQuestion2Answer());
		temp.setQuestion2Score(answer.getQuestion2Score());
		temp.setQuestion3Answer(answer.getQuestion3Answer());
		temp.setQuestion3Score(answer.getQuestion3Score());
		temp.setQuestion4Answer(answer.getQuestion4Answer());
		temp.setQuestion4Score(answer.getQuestion4Score());
		temp.setQuestion5Answer(answer.getQuestion5Answer());
		temp.setQuestion5Score(answer.getQuestion5Score());
		temp.setQuestion6Answer(answer.getQuestion6Answer());
		temp.setQuestion6Score(answer.getQuestion6Score());
		temp.setQuestion7Answer(answer.getQuestion7Answer());
		temp.setQuestion7Score(answer.getQuestion7Score());
		temp.setQuestion8Answer(answer.getQuestion8Answer());
		temp.setQuestion8Score(answer.getQuestion8Score());
		temp.setQuestion9Answer(answer.getQuestion9Answer());
		temp.setQuestion9Score(answer.getQuestion9Score());
		temp.setTaskStatu(2);
		answerCardDao.update(temp);
		return "提交成功！";
	}

	@Override
	public int addQuestion(QuestionModel q) {
		// TODO Auto-generated method stub
		int result = -1;
		if (q != null)
			result = questionDao.insert(q);
		return result;
	}

	@Override
	public int arrangementWork(TestPaperModel test) {
		// TODO Auto-generated method stub
		int result = -1;
		if (test != null)
			result = testPaperDao.insert(test);
		return result;
	}

	@Override
	public int addTask(TaskModel t) {
		// TODO Auto-generated method stub
		int result = -1;
		if (t != null)
			result = taskDao.insert(t);
		return result;
	}

	@Override
	public List<UserinfoModel> findStudent(String key) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel t where t.userType=1 and ( t.username like :param or t.telephone like '%"
				+ key + "%' )";
		List<UserinfoModel> users = userinfoDao.likeFindList(hql, key);
		List<UserinfoModel> result = new ArrayList<UserinfoModel>();

		if (users == null)
			return result;
		for (UserinfoModel u : users) {
			UserinfoModel temp = new UserinfoModel();
			temp.setUserId(u.getUserId());
			temp.setUsername(u.getUsername());
			temp.setTelephone(u.getTelephone());
			result.add(temp);
		}
		return result;
	}

	@Override
	public int addStudent(int student_id, int task_id) {
		// TODO Auto-generated method stub
		AnswerCardModel a = new AnswerCardModel();
		a.setAnswerUserId(student_id);
		a.setTaskId(task_id);
		a.setTaskStatu(0);
		int result = -1;
		if (a != null)
			result = answerCardDao.insert(a);
		return result;
	}

	@Override
	public List<TaskRespModel> getTeacherTaskList(int teacher_id,
			int task_statu, int page_count, int page_num) {
		// TODO Auto-generated method stub
		String hql = "from TaskModel t where t.teacherId=" + teacher_id;

		List<TaskRespModel> result = new ArrayList<TaskRespModel>();
		List<TaskModel> ts = taskDao.findPageList(page_count, page_num, hql);
		if (ts == null)
			return null;
		for (TaskModel task : ts) {

			hql = "from UserinfoModel u where u.userId= " + task.getTeacherId();
			String name = userinfoDao.find(hql).getUsername();

			hql = "from AnswerCardModel u where u.taskId=" + task.getTaskId();
			List<AnswerCardModel> answerCardList = answerCardDao.findList(hql);
			taskRespModel = new TaskRespModel(task.getTaskId(),
					task.getTaskName(), name, task.getNewTime());

			taskRespModel.setTotalNumber(answerCardList.size());
			hql += " and u.taskStatu=2";
			answerCardList = answerCardDao.findList(hql);
			taskRespModel.setFinishNumber(answerCardList.size());
			if (task_statu == 0
					&& taskRespModel.getTotalNumber() != taskRespModel
							.getFinishNumber()) {
				result.add(taskRespModel);
			}
			if (task_statu == 1
					&& taskRespModel.getTotalNumber() == taskRespModel
							.getFinishNumber()) {
				result.add(taskRespModel);
			}

		}
		return result;
	}

	@Override
	public List<TeacherTaskDetialModel> getTeacherTaskDetial(int task_id) {
		// TODO Auto-generated method stub
		String hql = "from AnswerCardModel a where a.taskId=" + task_id;
		List<AnswerCardModel> answerCards = answerCardDao.findList(hql);
		List<TeacherTaskDetialModel> result = new ArrayList<TeacherTaskDetialModel>();
		UserinfoModel u = new UserinfoModel();

		if (answerCards == null)
			return null;

		for (AnswerCardModel a : answerCards) {

			hql = "from UserinfoModel a where a.userId=" + a.getAnswerUserId();
			u = userinfoDao.find(hql);
			TeacherTaskDetialModel tr = new TeacherTaskDetialModel(
					u.getUserId(), u.getUsername(), a.getTotalScore());
			result.add(tr);
		}
		return result;
	}

	@Override
	public AnswerCardModel getAnswerCard(int user_id, int task_id) {
		// TODO Auto-generated method stub
		String hql = "from AnswerCardModel a where a.taskId=" + task_id
				+ " and a.answerUserId=" + user_id;
		AnswerCardModel result = answerCardDao.find(hql);
		return result;
	}

	@Override
	public int getStudentTaskNew(int user_id) {
		// TODO Auto-generated method stub
		String hql = "from AnswerCardModel a where a.answerUserId="
				+ user_id
				+ "and ( a.taskStatu=0 or a.taskStatu=-1) order by answerCardId desc";
		List<AnswerCardModel> result = answerCardDao.findList(hql);
		if (result != null) {
			if (result.size() > 0) {
				AnswerCardModel a = result.get(0);
				if (a.getTaskStatu() == -1) {
					for (AnswerCardModel b : result) {
						if (b.getTaskStatu() == -1) {
							b.setTaskStatu(0);
							answerCardDao.update(b);
						}
					}
					return result.size();
				}
			}
		}
		return 0;
	}
}
