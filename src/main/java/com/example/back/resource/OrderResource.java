package com.example.back.resource;

import com.example.back.dto.CreateOrderRequest;
import com.example.back.dto.GetAllOrdersDto;
import com.example.back.model.Orderr;
import com.example.back.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Tag(name = "Orders", description = "Orders ecommerce api v1.0.0")
@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService ) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getAllOrders(GetAllOrdersDto requestDto){
        Map<String, Object> response = orderService.getAllOrders(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

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

    @PostMapping("/{userId}")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest requestDto,
                                         @PathVariable Integer userId){
        int id = orderService.createOrder(requestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
