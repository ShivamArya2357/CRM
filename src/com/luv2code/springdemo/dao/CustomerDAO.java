package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomers(int customerId);

    void deleteCustomer(int customerId);

    List<Customer> searchCustomers(String searchName);
}
