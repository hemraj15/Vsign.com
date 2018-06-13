/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.ProductCatagory;
import com.vsign.tech.data.dto.ProductCategoryDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class ProductCategaryDaoImpl extends GenericDaoImpl<ProductCatagory, Long> implements ProductCategaryDao {
	private Logger				LOGGER	= LoggerFactory.getLogger(ProductCategaryDaoImpl.class);
	
	@Override
	public List<ProductCategoryDto> fetchAllProductsCategoriesByStatus(String status) {
		List<ProductCategoryDto> prodCatDtos=null;
		
	
				LOGGER.info(">> getStateDaoList");
	
		Criteria criteria = getCriteria().add(Restrictions.eq("status", status))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(ProductCategoryDto.class));
		prodCatDtos = criteria.list();
		LOGGER.debug("<< getStateDaoList" + prodCatDtos.toString());
		return prodCatDtos;
	}

	@Override
	public List<ProductCatagory> fetchAllProductsCategories(String status) {
		List<ProductCatagory> prodCatDtos=null;
		
	
				LOGGER.info(">> getStateDaoList");
	
		Criteria criteria = getCriteria().add(Restrictions.eq("status", status));
		       
		prodCatDtos = criteria.list();
		LOGGER.debug("<< getStateDaoList" + prodCatDtos.toString());
		return prodCatDtos;
	}
}
