package com.microservice.marketplace.controller;

import com.microservice.marketplace.entity.Customer;
import com.microservice.marketplace.model.CustomerDTO;
import com.microservice.marketplace.model.ResponseDTO;
import com.microservice.marketplace.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ResponseDTO> getCustomerInformation(){
        ResponseDTO responseDTO = customerService.getAllCustomer();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody CustomerDTO customer){
        ResponseDTO responseDTO = customerService.createCustomer(customer);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(path = "/id={id}")
    public ResponseEntity<ResponseDTO> getCustomerById(@PathVariable Long id){
        ResponseDTO responseDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDTO> getCustomerById(@RequestBody CustomerDTO customerDTO){
        ResponseDTO responseDTO = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(path = "/delete-id={id}")
    public ResponseEntity<ResponseDTO> deleteCustomerById(@PathVariable Long id){
        ResponseDTO responseDTO = customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(responseDTO);
    }
}
