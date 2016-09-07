package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.respModel.ResourceRespModel;

public interface ResourceService {
	
	/**
	 * 获取热门资源
	 * @return
	 */
	public List<ResourceRespModel>getHotResource();

	/**
	 * 根据文件类型 课程类型 分页按顺序返回list数据
	 * @param resource_course_type
	 * @param resource_file_type
	 * @param sort_type
	 * @param page_count
	 * @param page_number
	 * @return
	 */
	public List<ResourceRespModel> getResourceList(int resource_course_type,int sort_type,
			int resource_file_type,  int page_count,
			int page_number,int user_id);

	/**
	 * 获取详细的资源信息
	 * @param resource_id
	 */
	public ResourceRespModel getResourceInfo(String resource_id);

	/**
	 * 下载
	 * @param user_id
	 * @param resource_id
	 * @return
	 */
	public String download( String resource_id);

	
	/**
	 * 获取资源链接
	 * @param resource_id
	 * @return 
	 */
	public String getResourceUrl(String resource_id);

	/**
	 * 上传资源接口
	 * @param user_id
	 * @param resource_name
	 * @param resource_url
	 * @param resource_describe
	 * @param resource_course_type
	 * @param resource_file_type
	 * @return 
	 */
	public String upload(String user_id, String resource_name,
			String resource_url, String resource_describe,
			String resource_course_type, int resource_file_type);

	/**
	 * 获取分享列表
	 * @param string
	 * @return
	 */
	public List<ResourceRespModel> getUploadList(String user_id);

	/**
	 * 收藏
	 * @param resource_id
	 * @param user_id
	 * @return
	 */
	public String collect(int resource_id, int user_id);
	
}
