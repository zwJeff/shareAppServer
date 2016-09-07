package com.jeff.serviceImp;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;
import com.jeff.service.IndexService;

@Service("IndexService")
public class IndexServiceImp extends BaseService implements IndexService {

	@Override
	public List<PictureListModel> getPictureNewsList() {
		// TODO Auto-generated method stub
		String hql = "from PictureListModel p ";

		List<PictureListModel> result = pictureListDao.findList(hql);

		return result;

	}

	@Override
	public List<CourseTypeModel> getCourse(int index) {
		// TODO Auto-generated method stub
		String hql;
		if (index == 1)
			hql = "from CourseTypeModel c where c.isIndexCourse=1";
		else
			hql = "from CourseTypeModel c";
		List<CourseTypeModel> result = courseTypeDao.findList(hql);

		return result;
	}

	@Override
	public List<CourseTypeModel> getAllCourse() {
		// TODO Auto-generated method stub
		String hql = "from CourseTypeModel";

		List<CourseTypeModel> result = courseTypeDao.findList(hql);

		return result;
	}
}
