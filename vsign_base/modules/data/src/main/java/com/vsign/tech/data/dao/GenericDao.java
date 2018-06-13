package com.vsign.tech.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.vsign.tech.data.exception.InstanceNotFoundException;

public interface GenericDao<E, PK extends Serializable> {

	/**
	 * @param entity
	 * @return value of inserted ID
	 */
	PK save(E entity);

	void saveOrUpdate(E entity);

	void persist(E entity);

	/**
	 * Remove the specified entity from the database.
	 * 
	 * @param entity
	 */
	void remove(E entity) throws InstanceNotFoundException;

	/**
	 * Remove the entity with the specified type and id from the database.
	 * 
	 * @param id
	 */
	void remove(PK id) throws InstanceNotFoundException;

	/**
	 * Remove all the entities of the given type from the database.
	 * 
	 * @return Id's
	 */
	int removeAll() throws InstanceNotFoundException;

	/**
	 * Update the given entity
	 * 
	 * @param entity
	 */
	E update(E entity);

	/**
	 * Returns true if the entity exists of given id
	 * 
	 * @param id
	 * @return boolean
	 */
	boolean exists(PK id);

	/**
	 * Get the entity with the specified type and id from the database.
	 * 
	 * @param id
	 * @return entity
	 */
	E find(PK id) throws InstanceNotFoundException;

	/**
	 * load the entity with the specified type and id from the database.
	 * 
	 * @param id
	 * @return entity
	 */
	E load(PK id);

	/**
	 * Returns the total number of results.
	 * 
	 * @return total no of records
	 */
	Long count();

	/**
	 * Returns the total number of results that would be returned using the given matched Criteria.
	 * 
	 * @param DetachedCriteria
	 * @return no. of records
	 */
	Long count(DetachedCriteria criteria);

	/**
	 * Returns the total number of results that would be returned using the given matched Criteria.
	 * 
	 * @param Criteria
	 * @return no. of records
	 */
	Long count(Criteria criteria);

	/**
	 * Get a list of all the objects of the specified type.
	 * 
	 * @return list of records
	 */
	List<E> getEntities();

	/**
	 * return list of all the objects of the given Criterion.
	 * 
	 * @param paramArrayOfCriterion
	 * @return list of all objects
	 */
	List<E> searchByCriteria(Criterion... paramArrayOfCriterion);

	/**
	 * return list of all the objects of the given matched DetachedCriteria
	 * 
	 * @param criteria
	 * @return list of all objects
	 */
	List<E> searchByCriteria(DetachedCriteria criteria);

	/**
	 * return list of all the objects of the given matched Criteria
	 * 
	 * @param criteria
	 * @return list of all objects
	 */
	List<E> searchByCriteria(Criteria criteria);

	/**
	 * return list of all records by selected fields and their corresponding values
	 * 
	 * @param properties
	 * @param values
	 * @return list of all objects
	 */
	List<E> searchByProperties(String[] properties, Object[] values);

	/**
	 * return list of all records by given example
	 * 
	 * @param example
	 * @return list of all objects
	 */
	List<E> getByExample(E example);

	/**
	 * return list of all records by given example with given excludeProperty values
	 * 
	 * @param example
	 * @param excludeProperty
	 * @return list of all objects
	 */
	List<E> getByExample(E example, String[] excludeProperty);

	/**
	 * return list of all records by given example with given firstResult and maxResults values
	 * 
	 * @param example
	 * @param firstResult
	 * @param maxResults
	 * @return list of all objects
	 */
	List<E> getPagedListByExample(E example, int firstResult, int maxResults);

	/**
	 * return list of all records by given criteria with given firstResult and maxResults values
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return list of all objects
	 */
	List<E> getPagedListByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

	/**
	 * return list of all records by given criteria with given firstResult and maxResults values
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return list of all objects
	 */
	List<E> getPagedListByCriteria(Criteria criteria, int firstResult, int maxResults);

	/**
	 * return object by given criteria
	 * 
	 * @param criteria
	 * @return object
	 */
	Object getByCriteria(Criteria criteria) throws InstanceNotFoundException;

}
