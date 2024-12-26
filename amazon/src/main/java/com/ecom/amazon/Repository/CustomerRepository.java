package com.ecom.amazon.Repository;

import com.ecom.amazon.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //is user exist by email
    boolean existsByEmail(String email);

    //find first user by email
    Customer findFirstByEmail(String email);
}