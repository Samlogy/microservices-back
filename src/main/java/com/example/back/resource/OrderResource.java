package com.example.back.resource;

import com.example.back.dto.Order.CreateOrderRequestDto;
import com.example.back.dto.Order.GetOrdersRequestDto;
import com.example.back.model.OrderItem;
import com.example.back.model.Orderr;
import com.example.back.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//@Tag(name = "Orders", description = "Orders ecommerce api v1.0.0")
@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService ) {
        this.orderService = orderService;
    }

//    @GetMapping()
//    public ResponseEntity<?> getAllOrders(@RequestParam(required = true) Integer userId){
//        List<Orderr> response = orderService.getAllOrders();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Integer orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> confirmOrder(@PathVariable Integer orderId){
        int id =  orderService.confirmOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto requestDto,
                                         @RequestParam(required = true) Integer userId){
       orderService.createOrder(requestDto, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orderr> getOrderById(@PathVariable Integer orderId) {
        Orderr order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
