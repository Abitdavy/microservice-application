package com.microservice.marketplace.service;

import com.microservice.marketplace.entity.Customer;
import com.microservice.marketplace.model.CustomerDTO;
import com.microservice.marketplace.model.ResponseDTO;
import com.microservice.marketplace.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepositories customerRepositories;

    public CustomerService(CustomerRepositories customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    public ResponseDTO getAllCustomer(){
        List<Customer> customerList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        try {
            customerList = customerRepositories.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (customerList.isEmpty()){
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data not found");
            responseDTO.setData(customerList);
        } else {
            responseDTO.setStatus("success");
            responseDTO.setMessage("data found");
            responseDTO.setData(customerList);
        }

        return responseDTO;
    }

    public ResponseDTO createCustomer(CustomerDTO customer){
        List<Customer> customerList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();
        Customer customerEntity = new Customer();

        if (customer.getCustomerName().isEmpty() || customer.getCustomerAge().isEmpty() || customer.getCustomerEmail().isEmpty()) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data yang diinput kosong");
            responseDTO.setData(customerList);
        } else {
            customerEntity.setCreatedDate(LocalDateTime.now().toString());
            customerEntity.setCustomerAge(customer.getCustomerAge());
            customerEntity.setCustomerEmail(customer.getCustomerEmail());
            customerEntity.setCustomerName(customer.getCustomerName());

            try {
                customerRepositories.save(customerEntity);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }

            customerList.add(customerEntity);
            responseDTO.setStatus("success");
            responseDTO.setMessage("data created");
            responseDTO.setData(customerList);
        }

        return responseDTO;
    }

    public ResponseDTO getCustomerById(Long id){
        List<Customer> customerList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        Customer customerEntity = customerRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customerList.add(customerEntity);

        responseDTO.setStatus("success");
        responseDTO.setMessage("data found");
        responseDTO.setData(customerList);
        return responseDTO;
    }

    public ResponseDTO updateCustomer(CustomerDTO customerDTO){
        List<Customer> customerList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        if (customerDTO.getCustomerName().isEmpty() || customerDTO.getCustomerAge().isEmpty() || customerDTO.getCustomerEmail().isEmpty()) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data yang diinput kosong");
            responseDTO.setData(customerList);
        } else {
            Customer customerEntity = customerRepositories.findById(customerDTO.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

            customerEntity.setCustomerName(customerDTO.getCustomerName());
            customerEntity.setCustomerAge(customerDTO.getCustomerAge());
            customerEntity.setCustomerEmail(customerDTO.getCustomerEmail());
            customerEntity.setUpdatedDate(LocalDateTime.now().toString());

            try {
                customerRepositories.save(customerEntity);
                customerList.add(customerEntity);
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }

            responseDTO.setStatus("success");
            responseDTO.setMessage("data saved");
            responseDTO.setData(customerList);
        }

        return responseDTO;
    }

    public ResponseDTO deleteCustomer(Long id){
        List<Customer> customerList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        Customer customerEntity = customerRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customerList.add(customerEntity);
        customerRepositories.deleteById(id);

        responseDTO.setStatus("success");
        responseDTO.setMessage("data deleted");
        responseDTO.setData(customerList);
        return responseDTO;
    }
}
