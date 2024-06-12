package genspark.CustomerManagement.CustomerManagementAPI.Services;

import genspark.CustomerManagement.CustomerManagementAPI.Entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(long customerid);
    Customer getCustomerByName(String name);
    Customer getCustomerByEmail(String email);
    Customer getCustomerByPhoneNumber(String phoneNumber);
    List<Customer> getCustomersByNameSort();
    List<Customer> getCustomersByEmailSort();
    List<Customer> getCustomersByPhoneNumberSort();
    Customer addNewCustomer(Customer newCustomer);
    Customer updateCustomer(Customer customer);
    String deleteCustomerByID(long customerid);
}
