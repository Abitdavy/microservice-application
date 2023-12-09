package com.microservice.marketplace.service;

import com.microservice.marketplace.entity.Customer;
import com.microservice.marketplace.entity.Order;
import com.microservice.marketplace.kafka.producer.OrderProducer;
import com.microservice.marketplace.model.OrderDTO;
import com.microservice.marketplace.model.ProductDTO;
import com.microservice.marketplace.model.ResponseDTO;
import com.microservice.marketplace.repositories.CustomerRepositories;
import com.microservice.marketplace.repositories.OrderRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class OrderService {
    private static final String PRODUCT_SERVICE_URL = "http://localhost:9090/product/";
    private final OrderProducer orderProducer;

    private final RestTemplate restTemplate;
    private final OrderRepositories orderRepositories;

    private final CustomerRepositories customerRepositories;


    public OrderService(OrderRepositories orderRepositories, CustomerRepositories customerRepositories, RestTemplate restTemplate, OrderProducer orderProducer) {
        this.orderRepositories = orderRepositories;
        this.customerRepositories = customerRepositories;
        this.restTemplate = restTemplate;
        this.orderProducer = orderProducer;
    }

    public ResponseDTO getAllOrder(){
        List<Order> orderList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        try {
            orderList = orderRepositories.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (orderList.isEmpty()){
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data not found");
            responseDTO.setData(orderList);
        } else {
            responseDTO.setStatus("success");
            responseDTO.setMessage("data found");
            responseDTO.setData(orderList);
        }

        return responseDTO;
    }

    public ResponseDTO createOrder(OrderDTO orderDTO){
        List<Order> orderList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();
        Order orderEntity = new Order();

        if (orderDTO.getCustomer().getCustomerName().isEmpty() || orderDTO.getCustomer().getCustomerAge().isEmpty() || orderDTO.getCustomer().getCustomerEmail().isEmpty()) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data yang diinput kosong");
            responseDTO.setData(orderList);
        } else {
            orderEntity.setCustomer(orderDTO.getCustomer());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<ProductDTO>> entity = new HttpEntity<>(orderDTO.getProducts(), headers);
            restTemplate.exchange(
                    (PRODUCT_SERVICE_URL + "order-product"),
                    HttpMethod.PUT, entity,
                    new ParameterizedTypeReference<List<ProductDTO>>() {}
            );

            try {
                customerRepositories.save(orderEntity.getCustomer());
                orderRepositories.save(orderEntity);
                log.info("send kafka");
                orderProducer.sendMessageOrder(orderEntity);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }

            orderList.add(orderEntity);
            responseDTO.setStatus("success");
            responseDTO.setMessage("data created");
            responseDTO.setData(orderList);
        }

        return responseDTO;
    }

    public ResponseDTO getOrderById(String id){
        List<Order> orderList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        Order orderEntity = orderRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderList.add(orderEntity);

        responseDTO.setStatus("success");
        responseDTO.setMessage("data found");
        responseDTO.setData(orderList);
        return responseDTO;
    }

    public ResponseDTO updateOrder(OrderDTO orderDTO){
        List<Order> orderList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        if (orderDTO.getCustomer().getCustomerName().isEmpty() || orderDTO.getCustomer().getCustomerAge().isEmpty() || orderDTO.getCustomer().getCustomerEmail().isEmpty()) {
            responseDTO.setStatus("failed");
            responseDTO.setMessage("data yang diinput kosong");
            responseDTO.setData(orderList);
        } else {
            Order orderEntity = orderRepositories.findById(orderDTO.getOrderId()).orElseThrow(() -> new IllegalArgumentException("Order not found"));

            orderEntity.setCustomer(orderDTO.getCustomer());

            try {
                orderRepositories.save(orderEntity);
                orderList.add(orderEntity);
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }

            responseDTO.setStatus("success");
            responseDTO.setMessage("data saved");
            responseDTO.setData(orderList);
        }

        return responseDTO;
    }

    public ResponseDTO deleteOrder(String id){
        List<Order> orderList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO<>();

        Order orderEntity = orderRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        orderList.add(orderEntity);
        orderRepositories.deleteById(id);

        responseDTO.setStatus("success");
        responseDTO.setMessage("data deleted");
        responseDTO.setData(orderList);
        return responseDTO;
    }
}
