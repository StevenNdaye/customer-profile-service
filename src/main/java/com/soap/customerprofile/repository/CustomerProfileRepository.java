package com.soap.customerprofile.repository;


import com.soap.customerprofile.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by steven on 2014/11/09.
 */
@Repository
public interface CustomerProfileRepository extends CrudRepository<Customer, Integer>{
}
