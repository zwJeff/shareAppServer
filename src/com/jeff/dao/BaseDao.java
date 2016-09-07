package com.jeff.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jeff.model.UserinfoModel;

public abstract class BaseDao<T> {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int insert(T t) {
		// TODO Auto-generated method stub
		int result;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			result = Integer.parseInt(session.save(t).toString());
			tx.commit();
			session.close();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

	public int delete(T t) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(t);
			tx.commit();
			session.close();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

	public int update(T t) {
		// TODO Auto-generated method stub
		// try {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
		session.close();
		return 0;
		// } catch (Exception e) {
		// // TODO: handle exception
		// return -1;
		// }
	}

	public List<T> findList(String hql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<T> ts = session.createQuery(hql).list();
		tx.commit();
		session.close();
		if (ts.size() > 0) {
			return ts;
		}
		return ts;
	}

	public List<T> likeFindList(String hql, String key) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setString("param", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<T> ts = q.list();
		tx.commit();
		session.close();
		if (ts.size() > 0) {
			return ts;
		}
		return null;
	}

	public <R> List<R> findPageList(int count, int page, String hql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery(hql);
		query.setFirstResult(page * count);
		query.setMaxResults(count);
		List<R> ts = (List<R>) query.list();

		tx.commit();
		session.close();

		if (ts.size() > 0) {
			return ts;
		}

		return null;
	}

	public T find(String hql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<T> ts = session.createQuery(hql).list();
		tx.commit();
		session.close();
		if (ts.size() > 0) {
			return ts.get(0);
		}
		return null;
	}

	public List<HashMap> findTables(String hql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<HashMap> ts = session.createQuery(hql).list();
		tx.commit();
		session.close();
		return ts;
	}

	public boolean isTokenValid(String token) {

		String hql = "from UserinfoModel u where u.token= '" + token + "'";
		Session session = sessionFactory.openSession();
		List<UserinfoModel> users = session.createQuery(hql).list();
		session.close();
		UserinfoModel userinfo = users.size() > 0 ? users.get(0) : null;
		if (userinfo == null) {
			return false;
		} else {
			return true;
		}

	}
}
