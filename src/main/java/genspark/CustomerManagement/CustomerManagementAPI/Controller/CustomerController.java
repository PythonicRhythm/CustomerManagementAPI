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
        StringBuilder allHTML = new StringBuilder();

        allHTML.append("<style>" +
                ".homepanel {" +
                "display: flex;" +
                "flex-direction: column;" +
                "margin: 20;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".homepanel > h1 {" +
                "margin: 0;" +
                "margin-bottom: 10;" +
                "font-size: 50;" +
                "}" +
                ".homepanel > a {" +
                "font-size: 30;" +
                "}" +
                "</style>");

        allHTML.append("<div class=\"homepanel\">");
        allHTML.append("<h1>Welcome to the Customer Management System!</h1>");
        allHTML.append("<a href=\"/customers\">View all current customers.</a>");
        allHTML.append("</div>");
        return allHTML.toString();
    }


    private String customerComponentBuilder(Customer c)
    {
        StringBuilder childHTML = new StringBuilder();
        childHTML.append(String.format("<h2>Customer (ID: %d):</h2>", c.getCustomerId()));
        childHTML.append(String.format("<p>Name: %s</p>", c.getName()));
        childHTML.append(String.format("<p>Email: %s</p>", c.getEmail()));
        childHTML.append(String.format("<p>Phone Number: %s</p>", c.getPhoneNumber()));
        childHTML.append(String.format("<a href=\"/customers/%d\">View Customer</a>", c.getCustomerId()));
        return childHTML.toString();
    }

    private String customerPanelBuilder(List<Customer> customers)
    {
        StringBuilder allHTML = new StringBuilder();

        // CSS Styling
        allHTML.append("<style>" +
                ".customerpanel {" +
                    "display: flex;" +
                    "flex-direction: row;" +
                    "flex-wrap: wrap;" +
                    "align-items: stretch;" +
                    "justify-content: space-evenly;" +
                    "border-top: 1px solid black;" +
                "}" +
                ".customercomponent {" +
                    "width: 30%;" +
                    "height: 100%;" +
                    "border: 1px solid black;" +
                    "border-radius: 10% / 50%;" +
                    "margin-top: 10;" +
                    "display: flex;" +
                    "flex-direction: column;" +
                    "align-items: center;" +
                    "justify-content: center;" +
                "}" +
                ".customercomponent > p {" +
                    "margin: 2;" +
                "}" +
                ".customercomponent > a {" +
                    "margin: 5;" +
                "}" +
                ".header {" +
                    "display: flex;" +
                    "flex-direction: column;" +
                    "align-items: center;" +
                    "justify-content: center;" +
                    "padding-bottom: 20;" +
                "}" +
                ".header > h1 {" +
                    "margin-bottom: 5;" +
                    "font-size: 60;" +
                "}" +
                ".header > a {" +
                    "margin-bottom: 3;" +
                "}" +
                "</style>");

        allHTML.append("<div class=\"header\">");
        allHTML.append("<h1>ALL CUSTOMERS</h1>");

        if(customers.isEmpty()) {
            allHTML.append("<h2>No Customers found. Create some via Postman!</h2>");
            allHTML.append("</div>");
            return allHTML.toString();
        }

        // Create sorting links
        allHTML.append("<a href=\"/customers/name\"><button>Sort by Name</button></a>");
        allHTML.append("<a href=\"/customers/email\"><button>Sort by Email</button></a>");
        allHTML.append("<a href=\"/customers/phoneNumbers\"><button>Sort by Phone Numbers</button></a>");
        allHTML.append("<a href=\"/customers\"><button>Default ID Sort</button></a>");

        // Close header container
        allHTML.append("</div>");

        allHTML.append("<div class=\"customerpanel\">");
        for(Customer c: customers) {
            log.debug(String.format("Found Customer %d, creating html component for customer.", c.getCustomerId()));
            allHTML.append("<div class=\"customercomponent\">");
            allHTML.append(customerComponentBuilder(c));
            allHTML.append("</div>");
        }
        allHTML.append("</div>");
        return allHTML.toString();
    }

    private String customerPageBuilder(Customer c)
    {
        StringBuilder allHTML = new StringBuilder();

        allHTML.append("<style>" +
                ".pagepanel {" +
                "display: flex;" +
                "flex-direction: row;" +
                "height: 100%;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".customercomp {" +
                "display: flex;" +
                "flex-direction: column;" +
                "border: 1px solid black;" +
                "border-radius: 20px;" +
                "padding: 20;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".customercomp > p {" +
                "font-size: 30px;" +
                "margin: 0;" +
                "margin-bottom: 10;" +
                "}" +
                ".customercomp > h1 {" +
                "font-size: 60px;" +
                "}" +
                "</style>");

        allHTML.append("<div class=\"pagepanel\">");
        allHTML.append("<div class=\"customercomp\">");

        if(c == null) {
            log.info("Customer not found, creating null page...");
            allHTML.append("<h1>Customer Not Found!</h1>");
            allHTML.append("<a href=\"/customers\"><button>Go Back</button></a>");
            allHTML.append("</div>");
            allHTML.append("</div>");
        }
        else {
            log.info("Customer found, creating customer page...");
            allHTML.append("<a href=\"/customers\"><button>Go Back</button></a>");
            allHTML.append(String.format("<h1>Customer (ID: %d):</h1>", c.getCustomerId()));
            allHTML.append(String.format("<p><b>Name:</b> %s</p>", c.getName()));
            allHTML.append(String.format("<p><b>Email:</b> %s</p>", c.getEmail()));
            allHTML.append(String.format("<p><b>Phone Number:</b> %s</p>", c.getPhoneNumber()));

        }

        allHTML.append("</div>");
        allHTML.append("</div>");


        return allHTML.toString();
    }

    @GetMapping("/customers")
    public String getCustomers()
    {
        log.info("Entering all customers page...");
        List<Customer> customers = cs.getAllCustomers();
        return customerPanelBuilder(customers);
    }

    @GetMapping("/customers/name")
    public String getCustomersBySortedNames()
    {
        log.info("Entering all customers page with name sort...");
        List<Customer> customers = cs.getCustomersByNameSort();
        return customerPanelBuilder(customers);
    }

    @GetMapping("/customers/email")
    public String getCustomersBySortedEmails()
    {
        log.info("Entering all customers page with email sort...");
        List<Customer> customers = cs.getCustomersByEmailSort();
        return customerPanelBuilder(customers);
    }

    @GetMapping("/customers/phoneNumbers")
    public String getCustomersBySortedPhoneNumbers()
    {
        log.info("Entering all customers page with phone number sort...");
        List<Customer> customers = cs.getCustomersByPhoneNumberSort();
        return customerPanelBuilder(customers);
    }

    @GetMapping("/customers/{customerID}")
    public String getSingleCustomer(@PathVariable Long customerID)
    {
        log.info("Entering individual customer page...");
        Customer customer = cs.getCustomerById(customerID);
        return customerPageBuilder(customer);

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
