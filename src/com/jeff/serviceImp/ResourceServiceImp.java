package com.jeff.serviceImp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import mUtils.MUtils;
import com.jeff.dao.CourseTypeDao;
import com.jeff.dao.ResourceDao;
import com.jeff.dao.UserinfoDao;
import com.jeff.model.CollectListModel;
import com.jeff.model.CourseTypeModel;
import com.jeff.model.DownloadListModel;
import com.jeff.model.ResourceModel;
import com.jeff.model.UserinfoModel;
import com.jeff.respModel.ResourceRespModel;
import com.jeff.service.ResourceService;

@Service("ResourceService")
public class ResourceServiceImp extends BaseService implements ResourceService {

	@Override
	public List<ResourceRespModel> getHotResource() {
		// TODO Auto-generated method stub
		String hql = "from ResourceModel r order by (r.resourceDownloadTime+r.resourceCollectTime) desc";

		List<ResourceModel> temp = resourceDao.findList(hql);
		List<ResourceRespModel> result = new ArrayList<ResourceRespModel>();
		if (temp != null) {
			int total = temp.size() > 10 ? 10 : temp.size();
			for (int i = 0; i < total; i++) {
				ResourceRespModel r = new ResourceRespModel(temp.get(i)
						.getResourceId(), temp.get(i).getResourceName(), temp
						.get(i).getResourceFileType(), temp.get(i)
						.getResourceUploadTime());
				r.setAuthorName(userinfoDao.find(
						"from UserinfoModel r where r.userId ="
								+ temp.get(i).getResourceAuthor())
						.getUsername());
				result.add(r);
			}
		}
		return result;

	}

	@Override
	public ResourceRespModel getResourceInfo(String resource_id) {
		// TODO Auto-generated method stub
		String hql = "from ResourceModel r where r.resourceId=" + resource_id;
		ResourceModel temp = resourceDao.find(hql);

		ResourceRespModel result = new ResourceRespModel();

		result.setResourceId(temp.getResourceId());
		result.setResourceName(temp.getResourceName());
		result.setResourceUrl(temp.getResourceUrl());
		result.setResourceDescribe(temp.getResourceDescribe());
		result.setCourseId(temp.getResourceCourseType());
		result.setResourceFileType(temp.getResourceFileType());
		result.setResourceUploadTime(temp.getResourceUploadTime());
		result.setResourceDownloadTime(temp.getResourceDownloadTime());
		result.setResourceCollectTime(temp.getResourceCollectTime());

		UserinfoModel user = userinfoDao
				.find("from UserinfoModel r where r.userId="
						+ temp.getResourceAuthor());
		result.setAuthorName(user.getUsername());

		CollectListModel collect = collectListDao
				.find("from CollectListModel r where r.collectUserId="
						+ temp.getResourceAuthor()
						+ " and r.collectRecourceId=" + temp.getResourceId());
		if (collect != null)
			result.setIsCollected(1);
		else
			result.setIsCollected(0);

		return result;
	}

	@Override
	public String download(String resource_id) {
		// TODO Auto-generated method stub
		String hql = "from ResourceModel u where u.resourceId='" + resource_id
				+ "'";
		ResourceModel resource = resourceDao.find(hql);
		if (resource == null)
			return "1";
		resource.setResourceDownloadTime(resource.getResourceDownloadTime() + 1);
		resourceDao.update(resource);
		return "";
	}

	@Override
	public String getResourceUrl(String resource_id) {
		// TODO Auto-generated method stub
		String hql = "from ResourceModel u where u.resourceId='" + resource_id
				+ "'";
		ResourceModel resource = resourceDao.find(hql);
		if (resource == null)
			return null;
		if (resource.getResourceUrl() == null
				|| resource.getResourceUrl() == "")
			return null;
		return resource.getResourceUrl();
	}

	@Override
	public String upload(String user_id, String resource_name,
			String resource_url, String resource_describe,
			String resource_course_type, int resource_file_type) {
		// TODO Auto-generated method stub
		String hql = "from UserinfoModel u where u.userId='" + user_id + "'";
		UserinfoModel user = userinfoDao.find(hql);
		if (user == null)
			return "1";
		hql = "from CourseTypeModel c where c.courseId='"
				+ resource_course_type + "'";
		CourseTypeModel courseType = courseTypeDao.find(hql);
		if (courseType == null)
			return "3";
		ResourceModel resource = new ResourceModel(Integer.parseInt(user_id),
				Integer.parseInt(resource_course_type), resource_name,
				resource_url, resource_file_type, MUtils.getCurrentSqlDate(),
				0, 0);
		resource.setResourceDescribe(resource_describe);
		int resourceId = resourceDao.insert(resource);
		if (resourceId == -1) {
			return "2";
		}
		return resourceId + "";
	}

	@Override
	public List<ResourceRespModel> getUploadList(String user_id) {

		// TODO Auto-generated method stub
		String hql = "from ResourceModel r where r.resourceAuthor=" + user_id;

		List<ResourceRespModel> result = new ArrayList<ResourceRespModel>();

		List<ResourceModel> list = resourceDao.findList(hql);
		for (ResourceModel m : list) {
			result.add(new ResourceRespModel(m.getResourceId(), m
					.getResourceName(), m.getResourceFileType(), m
					.getResourceUploadTime()));
		}
		return result;

	}

	@Override
	public List<ResourceRespModel> getResourceList(int resource_course_type,
			int sort_type, int resource_file_type, int page_count,
			int page_number,int user_id) {
		// TODO Auto-generated method stub

		String hql = "from ResourceModel r";

		if (resource_file_type != -1)
			hql += " where r.resourceFileType =" + resource_file_type;
		if (resource_course_type != -1) {
			if(hql.contains("where"))
				hql+="and";
			else 
				hql+=" where";
			hql += " r.resourceCourseType = " + resource_course_type;
		}
		if (sort_type == 0)
			hql += " order by (r.resourceDownloadTime+r.resourceCollectTime) desc";
		else if (sort_type == 1)
			hql += " order by (r.resourceDownloadTime) desc";

		List<ResourceModel> temp = resourceDao.findPageList(page_count,
				page_number, hql);
		List<ResourceRespModel> result = new ArrayList<ResourceRespModel>();
		if(temp==null)
			return result ;
		for (ResourceModel r : temp) {
			hql = "from UserinfoModel u where u.userId ='"
					+ r.getResourceAuthor() + "'";
			String authorName = userinfoDao.find(hql).getUsername();
			
			ResourceRespModel rs=new ResourceRespModel(r.getResourceId(), authorName, r
					.getResourceCourseType(), r.getResourceName(), r
					.getResourceFileType(), r.getResourceUploadTime());
			hql = "from CollectListModel c where c.collectRecourceId ="+r.getResourceId()+" and c.collectUserId="+user_id;
			if(collectListDao.find(hql)!=null)
				rs.setIsCollected(1);
			else
				rs.setIsCollected(0);
			result.add(rs);
		}

		return result;
	}

	@Override
	public String collect(int resource_id, int user_id) {
		// TODO Auto-generated method stub
		String result;

		ResourceModel r=resourceDao.find("from ResourceModel r where r.resourceId="+resource_id);
		
		String hql = "from CollectListModel c where c.collectRecourceId ="
				+ resource_id + " and c.collectUserId=" + user_id;
		CollectListModel c = collectListDao.find(hql);
		if (c == null) {
			CollectListModel collectModel = new CollectListModel(resource_id,
					user_id, MUtils.getCurrentSqlDate());
			collectListDao.insert(collectModel);
			r.setResourceCollectTime(r.getResourceCollectTime()+1);
			resourceDao.update(r);
			result = "收藏成功！";
		} else {
			r.setResourceCollectTime(r.getResourceCollectTime()-1);
			resourceDao.update(r);
			collectListDao.delete(c);
			result = "取消收藏成功！";
		}
		return result;

	}
}
