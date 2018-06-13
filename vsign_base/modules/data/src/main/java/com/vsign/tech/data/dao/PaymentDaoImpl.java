/**
 * 
 */
package com.vsign.tech.data.dao;

import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.CustomerTransaction;

/**
 * @author Hemraj
 *
 */
@Repository
public class PaymentDaoImpl extends GenericDaoImpl<CustomerTransaction, Long> implements PaymentDao{

}
