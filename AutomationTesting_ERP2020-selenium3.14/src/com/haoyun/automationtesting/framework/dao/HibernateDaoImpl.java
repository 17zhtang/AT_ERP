package com.haoyun.automationtesting.framework.dao;

import org.springframework.stereotype.Repository;

@Repository(value = "testDao")
public class HibernateDaoImpl implements GenericDao {

	// @Autowired
	// @Qualifier("sessionFactory")
	// private SessionFactory sessionFactory;
	//
	// protected Session getSession() {
	// // 事务必须是开启的(Required)，否则获取不到
	// return sessionFactory.getCurrentSession();
	// }
	//
	// @Override
	// public <T> T findById(Class<T> type, Serializable id) {
	// return (T) getSession().get(type, id);
	// }
	//
	// @Override
	// public <T> List<T> findAll(Class<T> type) {
	// return null;
	// }
	//
	// @Override
	// public void save(Object... entities) {
	// for (Object entity : entities) {
	// getSession().save(entity);
	// }
	// }
	//
	// @Override
	// public void saveOrUpdate(Object entity) {
	// getSession().saveOrUpdate(entity);
	// }
	//
	// @Override
	// public void update(Object... entities) {
	// for (Object entity : entities) {
	// getSession().update(entity);
	// }
	// }
	//
	// @Override
	// public void delete(Object... entities) {
	// for (Object entity : entities) {
	// if (entity != null) {
	// getSession().delete(entity);
	// }
	// }
	// }
	//
	// @Override
	// public void deleteById(Class<?> type, Serializable id) {
	// if (id == null) {
	// return;
	// }
	// Object entity = findById(type, id);
	// if (entity == null) {
	// return;
	// }
	// delete(entity);
	// }
	//
	// @Override
	// public void refresh(Object... entities) {
	// for (Object entity : entities) {
	// getSession().refresh(entity);
	// }
	// }
	//
	// @Override
	// public void flush() {
	// getSession().flush();
	// }

}
