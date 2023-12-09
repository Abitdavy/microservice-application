package com.microservice.marketplace.controller;

import com.microservice.marketplace.entity.Order;
import com.microservice.marketplace.model.OrderDTO;
import com.microservice.marketplace.model.ProductDTO;
import com.microservice.marketplace.model.ResponseDTO;
import com.microservice.marketplace.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ResponseDTO> getOrderAll(){
        ResponseDTO responseDTO = orderService.getAllOrder();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody OrderDTO productDTO){
        ResponseDTO responseDTO = orderService.createOrder(productDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(path = "/id={id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable String id){
        ResponseDTO responseDTO = orderService.getOrderById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDTO> updateOrder(@RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = orderService.updateOrder(orderDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(path = "/delete-id={id}")
    public ResponseEntity<ResponseDTO> deleteCustomerById(@PathVariable String id){
        ResponseDTO responseDTO = orderService.deleteOrder(id);
        return ResponseEntity.ok().body(responseDTO);
    }
}
