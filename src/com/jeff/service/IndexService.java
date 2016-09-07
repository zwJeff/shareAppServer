package com.jeff.service;

import java.util.List;

import com.jeff.model.CourseTypeModel;
import com.jeff.model.PictureListModel;

public interface IndexService {

	List<PictureListModel> getPictureNewsList();
	
	List<CourseTypeModel>  getCourse(int index);

	List<CourseTypeModel> getAllCourse();
	
}
