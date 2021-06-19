package com.code.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.spring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	
	public List<Customer> getCustomers() {
		
		//hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//query.. sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
																Customer.class);
		
		
		//execute query
		List<Customer> customers = theQuery.getResultList();
		
		
		//return list
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession2 = sessionFactory.getCurrentSession();
		
		//save the customer to database
		currentSession2.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {

		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}


	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}
	


}
