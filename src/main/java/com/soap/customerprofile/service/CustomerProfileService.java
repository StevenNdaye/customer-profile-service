package com.soap.customerprofile.service;

import com.soap.customerprofile.generated.Customer;
import com.soap.customerprofile.repository.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.soap.customerprofile.util.Utils.ifNotNull;
import static java.util.Objects.isNull;

/**
 * Created by steven on 2014/11/09.
 */
@Component
public class CustomerProfileService {

    private CustomerProfileRepository customerProfileRepository;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository customerProfileRepository){
        this.customerProfileRepository = customerProfileRepository;
    }

    public List<Customer> getCustomers(){
        Iterable<com.soap.customerprofile.domain.Customer> returnedCustomers = customerProfileRepository.findAll();
        List<Customer> customers = new ArrayList<Customer>();

        for (com.soap.customerprofile.domain.Customer customer : returnedCustomers) {
            customers.add(convertFromRepositoryCustomer(customer));
        }

        return customers;
    }

    public Customer getCustomerById(int customerId){
        com.soap.customerprofile.domain.Customer customer = customerProfileRepository.findOne(customerId);
        return convertFromRepositoryCustomer(customer);
    }

    private Customer convertFromRepositoryCustomer(com.soap.customerprofile.domain.Customer customer) {
        Customer generatedCustomer = new Customer();
        generatedCustomer.setId(customer.getId());
        generatedCustomer.setName(customer.getName());
        generatedCustomer.setPhone(customer.getPhone());
        generatedCustomer.setAddress(customer.getAddress());
        generatedCustomer.setCityRegion(customer.getCityRegion());
        generatedCustomer.setCcNumber(customer.getCcNumber());

        return generatedCustomer;
    }

    public Customer createCustomer(Customer customer){
        com.soap.customerprofile.domain.Customer savedCustomer = convertToRepositoryCustomer(customer);
        return convertFromRepositoryCustomer(savedCustomer);
    }

    private com.soap.customerprofile.domain.Customer convertToRepositoryCustomer(Customer customer) {
        com.soap.customerprofile.domain.Customer customerToCreate = new com.soap.customerprofile.domain.Customer();
        customerToCreate.setId(customer.getId());
        customerToCreate.setName(customer.getName());
        customerToCreate.setPhone(customer.getPhone());
        customerToCreate.setAddress(customer.getAddress());
        customerToCreate.setCityRegion(customer.getCityRegion());
        customerToCreate.setCcNumber(customer.getCcNumber());

        return customerProfileRepository.save(customerToCreate);
    }

    public String deleteCustomer(int customerId){
        if(customerProfileRepository.exists(customerId)){
            customerProfileRepository.delete(customerId);
            return "Deleted!";
        }
        return "Not deleted!";

    }

    public Customer updateCustomer(int customerId, Customer customer){
        Customer returnedCustomer = getCustomerById(customerId);
        if(isNull(returnedCustomer)){
            return saveAfterConversion(customer);

        }
        return updateExistingCustomer(customer, returnedCustomer);
    }

    private Customer saveAfterConversion(Customer customer) {
        com.soap.customerprofile.domain.Customer toSave = convertToRepositoryCustomer(customer);
        return convertFromRepositoryCustomer(customerProfileRepository.save(toSave));
    }

    private Customer updateExistingCustomer(Customer customer, Customer returnedCustomer) {
        returnedCustomer.setId(ifNotNull(customer.getId(), returnedCustomer.getId()));
        returnedCustomer.setName(ifNotNull(customer.getName(), returnedCustomer.getName()));
        returnedCustomer.setPhone(ifNotNull(customer.getPhone(), returnedCustomer.getPhone()));
        returnedCustomer.setAddress(ifNotNull(customer.getAddress(), returnedCustomer.getAddress()));
        returnedCustomer.setCityRegion(ifNotNull(customer.getCityRegion(), returnedCustomer.getCityRegion()));
        returnedCustomer.setCcNumber(ifNotNull(customer.getCcNumber(), returnedCustomer.getCcNumber()));

        return saveAfterConversion(returnedCustomer);
    }
}
