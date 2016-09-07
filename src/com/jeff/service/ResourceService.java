package com.jeff.service;

import java.util.List;

import com.jeff.model.ResourceModel;
import com.jeff.respModel.ResourceRespModel;

public interface ResourceService {
	
	/**
	 * ��ȡ������Դ
	 * @return
	 */
	public List<ResourceRespModel>getHotResource();

	/**
	 * �����ļ����� �γ����� ��ҳ��˳�򷵻�list����
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
	 * ��ȡ��ϸ����Դ��Ϣ
	 * @param resource_id
	 */
	public ResourceRespModel getResourceInfo(String resource_id);

	/**
	 * ����
	 * @param user_id
	 * @param resource_id
	 * @return
	 */
	public String download( String resource_id);

	
	/**
	 * ��ȡ��Դ����
	 * @param resource_id
	 * @return 
	 */
	public String getResourceUrl(String resource_id);

	/**
	 * �ϴ���Դ�ӿ�
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
	 * ��ȡ�����б�
	 * @param string
	 * @return
	 */
	public List<ResourceRespModel> getUploadList(String user_id);

	/**
	 * �ղ�
	 * @param resource_id
	 * @param user_id
	 * @return
	 */
	public String collect(int resource_id, int user_id);
	
}
