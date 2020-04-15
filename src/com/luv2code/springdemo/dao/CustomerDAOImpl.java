package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer order by lastName",
                Customer.class
        );
        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomers(int customerId) {

        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, customerId);
        return customer;
    }

    @Override
    public void deleteCustomer(int customerId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", customerId);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {

        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from Customer where firstName=:name",
                    Customer.class
            );
            query.setParameter("name", searchName);
        } else {
            query = session.createQuery("from Customer", Customer.class);
        }
        List<Customer> customers = query.getResultList();
        return customers;
    }
}
