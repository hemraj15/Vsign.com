package com.vsign.tech.data.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import com.vsign.tech.data.dao.PrintStoreDao;
import com.vsign.tech.data.dao.entity.PrintStore;
import com.vsign.tech.data.exception.InstanceNotFoundException;

@ContextConfiguration(locations = { "classpath:assessment-api-db-test.xml" })
public class CompanyDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	PrintStoreDao printStoreDao;

	// @Test()
	public void test_getAllEntities() {
		List<PrintStore> printStores = printStoreDao.getEntities();
		Assert.assertEquals(printStores.size(), 2);
	}

	// @Test()
	public void test_saveEntity() {
		PrintStore printStore = new PrintStore();
		printStore.setStoreName("Itech");
		Long id = printStoreDao.save(printStore);
		Assert.assertNotNull(id);

		try {
			printStoreDao.remove(id);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
	}
}
