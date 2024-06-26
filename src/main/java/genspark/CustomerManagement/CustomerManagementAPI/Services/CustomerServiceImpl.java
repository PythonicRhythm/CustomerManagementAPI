package genspark.CustomerManagement.CustomerManagementAPI.Services;

import genspark.CustomerManagement.CustomerManagementAPI.Entity.Customer;
import genspark.CustomerManagement.CustomerManagementAPI.Repository.CustomerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerDAO CustomerDAO;

    public CustomerDAO getCustomerDAO() {
        return CustomerDAO;
    }

    @Override
    public List<Customer> getAllCustomers() {
        log.info("Attempting to acquire all Customers...");
        List<Customer> customers = CustomerDAO.findAll();

        if(customers.isEmpty())
            log.debug("No customers were found.");
        else
            log.info("Acquired all customers!");

        return customers;
    }

    @Override
    public Customer getCustomerById(long customerid) {
        log.info("Attempting to gather specific Customer by ID...");
        Optional<Customer> u = getCustomerDAO().findById(customerid);
        Customer customer = null;
        if(u.isPresent()) {
            log.debug("Customer was found by id!");
            customer = u.get();
        }
        else {
            log.debug("Customer was not found by given id!");
        }

        return customer;
    }

    @Override
    public Customer getCustomerByName(String name) {
        log.info("Attempting to gather specific Customer by name...");
        List<Customer> customers = getCustomerDAO().findCustomerByName(name);
        if(customers.isEmpty()) {
            log.debug("Found no customers by that name...");
            return null;
        }
        else if(customers.size() > 1) {
            log.debug("The amount of customers by that name is greater than 1. Returning the first one.");
            return customers.get(0);
        }
        else {
            log.debug("Customer was found with that name.");
            return customers.get(0);
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        log.info("Attempting to gather specific Customer by email...");
        List<Customer> customers = getCustomerDAO().findCustomerByEmail(email);
        if(customers.isEmpty()) {
            log.debug("Found no customers by that email...");
            return null;
        }
        else if(customers.size() > 1) {
            log.debug("The amount of customers by that email is greater than 1. Returning the first one.");
            return customers.get(0);
        }
        else {
            log.debug("Customer was found with that email.");
            return customers.get(0);
        }
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        log.info("Attempting to gather specific Customer by phone number...");
        List<Customer> customers = getCustomerDAO().findCustomerByPhoneNumber(phoneNumber);
        if(customers.isEmpty()) {
            log.debug("Found no customers by that phone number...");
            return null;
        }
        else if(customers.size() > 1) {
            log.debug("The amount of customers by that phone number is greater than 1. Returning the first one.");
            return customers.get(0);
        }
        else {
            log.debug("Customer was found with that phone number.");
            return customers.get(0);
        }
    }

    @Override
    public List<Customer> getCustomersByNameSort() {
        log.info("Attempting to order customer by names...");
        List<Customer> customers = getCustomerDAO().findCustomersByNameSort();
        log.info("Customers have been gathered by names!");
        return customers;
    }

    @Override
    public List<Customer> getCustomersByEmailSort() {
        log.info("Attempting to order customer by emails...");
        List<Customer> customers = getCustomerDAO().findCustomersByEmailSort();
        log.info("Customers have been gathered by emails!");
        return customers;
    }

    @Override
    public List<Customer> getCustomersByPhoneNumberSort() {
        log.info("Attempting to order customer by phone numbers...");
        List<Customer> customers = getCustomerDAO().findCustomersByPhoneNumberSort();
        log.info("Customers have been gathered by phone numbers!");
        return customers;
    }

    @Override
    public Customer addNewCustomer(Customer newCustomer) {
        log.info("Attempting to add a new customer...");
        Customer cust = getCustomerDAO().save(newCustomer);
        log.info("New customer has been added!");
        return cust;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        log.info("Attempting to update a current customer...");
        Customer cust = getCustomerDAO().save(customer);
        log.info("Customer has been updated!");
        return cust;
    }

    @Override
    public String deleteCustomerByID(long customerid) {
        log.info("Attempting to delete a current customer...");
        getCustomerDAO().deleteById(customerid);
        log.info("Customer Deleted Successfully!");
        return "Customer Deleted Successfully!";
    }
}
