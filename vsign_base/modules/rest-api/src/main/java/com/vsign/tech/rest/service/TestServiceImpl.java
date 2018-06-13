package com.vsign.tech.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.PrintStoreDao;
import com.vsign.tech.data.dao.entity.PrintStore;
import com.vsign.tech.data.exception.InstanceNotFoundException;

@Service
public class TestServiceImpl implements TestService {

	private Logger				LOGGER	= LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private PrintStoreDao			printStoreDao;

	@Override
	@Transactional
	public List<PrintStore> fetchAllCompanies() {
		LOGGER.info(">> fetchAllCompanies");
		List<PrintStore> printStores = printStoreDao.getEntities();
		LOGGER.debug("Companies : " + printStores);
		LOGGER.info("fetchAllCompanies <<");
		return printStores;
	}
/*
	@Override
	@Transactional
	public Long createEntity() {
		SkillSetCategory category = new SkillSetCategory();
		category.setCategoryName("IT");
		category.setDescription("IT Desc");
		return skillSetCategoryDao.save(category);
	}

	@Override
	@Transactional
	public void updateEntity(Long id) throws InstanceNotFoundException {
		SkillSetCategory category = skillSetCategoryDao.find(id);
		category.setCategoryName("ITTTT");
		category.setDescription("IT Descccc");
		skillSetCategoryDao.update(category);
	}
*/

	@Override
	public Long createEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEntity(Long id) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}
}