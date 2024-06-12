package genspark.CustomerManagement.CustomerManagementAPI.Repository;

import genspark.CustomerManagement.CustomerManagementAPI.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    @Query("SELECT c from Customer c WHERE c.name = :name")
    List<Customer> findCustomerByName(@Param("name") String name);

    @Query("SELECT c from Customer c WHERE c.email = :email")
    List<Customer> findCustomerByEmail(@Param("email") String email);

    @Query("SELECT c from Customer c WHERE c.phoneNumber = :phoneNumber")
    List<Customer> findCustomerByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT * from customer ORDER BY name", nativeQuery = true)
    List<Customer> findCustomersByNameSort();

    @Query(value = "SELECT * from customer ORDER BY email", nativeQuery = true)
    List<Customer> findCustomersByEmailSort();

    @Query(value = "SELECT * from customer ORDER BY phoneNumber", nativeQuery = true)
    List<Customer> findCustomersByPhoneNumberSort();

}
