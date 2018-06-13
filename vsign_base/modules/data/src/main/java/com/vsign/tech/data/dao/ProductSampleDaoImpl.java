/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.ProductSamples;

/**
 * @author Hemraj
 *
 */
@Repository
public class ProductSampleDaoImpl extends GenericDaoImpl<ProductSamples, Long> implements ProductSampleDao {

	@Override
	public List<ProductSamples> getSampleRecordByProductId(Long id) {
		List<ProductSamples> sampleList=null;
		
		Criteria crit=getCriteria().add(Restrictions.eq("product.id", id));
		sampleList=crit.list();
		
		return sampleList ;
	}

}
