package com.vsign.tech.data.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.exception.InstanceNotFoundException;

@SuppressWarnings({ "unchecked" })
public class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK> {

	private final Class<E>		entityClass;

	@Autowired
	private HibernateTemplate	ht;

	protected Class<E> getEntityClass() {
		return entityClass;
	}

	protected Session getSession() {
		return ht.getSessionFactory().getCurrentSession();
	}

	protected Criteria getCriteria() {
		return getSession().createCriteria(getEntityClass());
	}

	protected DetachedCriteria getDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	protected HibernateTemplate getHibernateTemplate() {
		return this.ht;
	}

	public GenericDaoImpl() {
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
		        .getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public PK save(E entity) {
		return (PK) ht.save(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		ht.saveOrUpdate(entity);
	}

	@Override
	public void persist(E entity) {
		ht.persist(entity);
	}

	@Override
	public void remove(E entity) throws InstanceNotFoundException {
		ht.delete(entity);
	}

	@Override
	public void remove(PK id) throws InstanceNotFoundException {
		remove(load(id));
	}

	@Override
	public int removeAll() throws InstanceNotFoundException {
		List<E> rows = getEntities();
		ht.deleteAll(rows);
		return rows.size();
	}

	@Override
	public E update(E entity) {
		return ht.merge(entity);
	}

	@Override
	public boolean exists(PK id) {
		return ht.get(getEntityClass(), id) != null;
	}

	@Override
	public E find(PK id) throws InstanceNotFoundException {
		E entity = ht.get(getEntityClass(), id);
		if (entity == null) {
			throw new InstanceNotFoundException(id, getEntityClass().getName());
		}
		return entity;
	}

	@Override
	public E load(PK id) {
		return ht.load(getEntityClass(), id);
	};

	@Override
	public Long count() {
		return (Long) getCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long count(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return DataAccessUtils.longResult(ht.findByCriteria(criteria));
	}

	@Override
	public Long count(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public List<E> getEntities() {
		return (List<E>) ht.find("from " + getEntityClass().getName() + " x");
	}

	@Override
	public List<E> searchByCriteria(Criterion... criterion) {
		DetachedCriteria crit = DetachedCriteria.forClass(getEntityClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (List<E>) ht.findByCriteria(crit);
	}

	@Override
	public List<E> searchByCriteria(DetachedCriteria criteria) {
		return (List<E>) ht.findByCriteria(criteria);
	}

	@Override
	public List<E> searchByProperties(String[] properties, Object[] values) {
		DetachedCriteria crit = DetachedCriteria.forClass(getEntityClass());
		int i = 0;
		for (String prop : properties) {
			crit.add(Restrictions.eq(prop, values[i]));
		}
		return (List<E>) ht.findByCriteria(crit);
	}

	@Override
	public List<E> getByExample(E exampleInstance) {
		return ht.findByExample(exampleInstance);
	}

	@Override
	public List<E> getByExample(E exampleInstance, String[] excludeProperty) {
		Example example = Example.create(exampleInstance);
		if (excludeProperty != null) {
			for (String str : excludeProperty) {
				example.excludeProperty(str);
			}
		}
		return (List<E>) ht.findByExample(example);
	}

	@Override
	public List<E> searchByCriteria(Criteria criteria) {
		return criteria.list();
	}

	@Override
	public Object getByCriteria(Criteria criteria) {
		return criteria.uniqueResult();
	}

	@Override
	public List<E> getPagedListByExample(E example, int firstResult, int maxResults) {
		return ht.findByExample(getEntityClass().getName(), example, firstResult, maxResults);
	}

	@Override
	public List<E> getPagedListByCriteria(DetachedCriteria criteria, int firstResult,
	        int maxResults) {
		return (List<E>) ht.findByCriteria(criteria, firstResult, maxResults);
	}

	@Override
	public List<E> getPagedListByCriteria(Criteria criteria, int firstResult, int maxResults) {
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

}
