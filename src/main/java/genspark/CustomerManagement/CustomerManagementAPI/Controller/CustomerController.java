package genspark.CustomerManagement.CustomerManagementAPI.Controller;

import genspark.CustomerManagement.CustomerManagementAPI.Entity.Customer;
import genspark.CustomerManagement.CustomerManagementAPI.Services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService cs;

    @GetMapping("/")
    public String home()
    {
        log.info("Entering home page...");
        return "<h1>Welcome to the Customer Management System!</h1>";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers()
    {
        log.info("Entering all customers page...");
        return cs.getAllCustomers();
    }

    @GetMapping("/customers/name")
    public List<Customer> getCustomersBySortedNames()
    {
        log.info("Entering all customers page with name sort...");
        return cs.getCustomersByNameSort();
    }

    @GetMapping("/customers/email")
    public List<Customer> getCustomersBySortedEmails()
    {
        log.info("Entering all customers page with email sort...");
        return cs.getCustomersByEmailSort();
    }

    @GetMapping("/customers/phoneNumbers")
    public List<Customer> getCustomersBySortedPhoneNumbers()
    {
        log.info("Entering all customers page with phone number sort...");
        return cs.getCustomersByPhoneNumberSort();
    }

    @GetMapping("/customers/{customerID}")
    public Customer getSingleCustomer(@PathVariable Long customerID)
    {
        log.info("Entering individual customer page...");
        return cs.getCustomerById(customerID);

    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        log.info("Request received to add new customer...");
        return cs.addNewCustomer(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer)
    {
        log.info("Request received to update customer...");
        return cs.updateCustomer(customer);
    }

    @DeleteMapping("/customers/{customerID}")
    public String deleteCustomer(@PathVariable Long customerID)
    {
        log.info("Request received to delete customer...");
        return cs.deleteCustomerByID(customerID);
    }

}
