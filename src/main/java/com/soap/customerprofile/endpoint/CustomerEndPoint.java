package com.soap.customerprofile.endpoint;

import com.soap.customerprofile.generated.*;
import com.soap.customerprofile.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by steven on 2014/11/09.
 */
@Endpoint
public class CustomerEndPoint {

    public static final String NAMESPACE_URI = "http://customerprofile.soap.com";

    private CustomerProfileService customerProfileService;

    @Autowired
    public CustomerEndPoint(CustomerProfileService customerProfileService){
        this.customerProfileService = customerProfileService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomers")
    @ResponsePayload
    public GetCustomersResponse getCustomers(@RequestPayload GetCustomers request){
        GetCustomersResponse response = new GetCustomersResponse();
        response.getReturn().addAll(customerProfileService.getCustomers());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerById")
    @ResponsePayload
    public GetCustomerByIdResponse getCustomerById(@RequestPayload GetCustomerById request){
        GetCustomerByIdResponse response = new GetCustomerByIdResponse();
        response.setReturn(customerProfileService.getCustomerById(request.getArg0()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCustomer")
    @ResponsePayload
    public CreateCustomerResponse createCustomer(@RequestPayload CreateCustomer request){
        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setReturn(customerProfileService.createCustomer(request.getCustomer()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCustomer")
    @ResponsePayload
    public DeleteCustomerResponse deleteCustomer(@RequestPayload DeleteCustomer request){
        DeleteCustomerResponse response = new DeleteCustomerResponse();
        response.setReturn(customerProfileService.deleteCustomer(request.getArg0()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCustomer")
    @ResponsePayload
    public UpdateCustomerResponse  updateCustomer(@RequestPayload UpdateCustomer request){
        UpdateCustomerResponse response = new UpdateCustomerResponse();
        response.setReturn(customerProfileService.updateCustomer(request.getArg0(), request.getCustomer()));
        return response;
    }
}
