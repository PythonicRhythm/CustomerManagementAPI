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
        log.info("Acquired all customers!");
        return customers;
    }

    @Override
    public Customer getCustomerById(long customerid) {
        log.info("Attempting to gather specific Customer by ID...");
        Optional<Customer> u = getCustomerDAO().findById(customerid);
        Customer Customer = null;
        if(u.isPresent()) {
            log.debug("Customer was found by id!");
            Customer = u.get();
        }
        else {
            log.debug("Customer was not found by given id!");
            throw new RuntimeException("Customer Not Found: "+customerid);
        }

        log.info("Returning the found customer!");
        return Customer;
    }

    @Override
    public Customer getCustomerByName(String name) {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return null;
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return null;
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
