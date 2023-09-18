package com.example.back.service;

import com.example.back.dto.CreateOrderRequest;
import com.example.back.dto.GetAllOrdersDto;
import com.example.back.exception.BadRequestionException;
import com.example.back.exception.NotFoundException;
import com.example.back.model.Orderr;
import com.example.back.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getAllOrders(GetAllOrdersDto requestDto) {
        List<Order> sortOrders = new ArrayList<Order>();
        Page<Orderr> pageOrders;

        String[] sorts = requestDto.getSorts();

        if (sorts[0].contains(",")) {
            for (String sortOrder : sorts) {
                String[] _sort = sortOrder.split(",");
                sortOrders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            sortOrders.add(new Order(getSortDirection(sorts[1]), sorts[0]));
        }
        Pageable pageRequest = PageRequest.of(requestDto.getPage(), requestDto.getSize(), Sort.by(sortOrders));

        pageOrders = orderRepository.findAll(pageRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("orders", pageOrders.getContent());
        response.put("currentPage", pageOrders.getNumber());
        response.put("totalItems", pageOrders.getTotalElements());
        response.put("totalPages", pageOrders.getTotalPages());
        return  response;
    }

    public int cancelOrder(Integer orderId) {
       Orderr order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));

        LocalDate currentDate = LocalDate.now();
        LocalDate orderDate = order.getOrderDate();
        LocalDate deadLineDate = orderDate.plusDays(2);

       if (currentDate.isBefore(deadLineDate) || currentDate.isEqual(deadLineDate)) {
           orderRepository.deleteById(orderId);
           return 1;
       }
        throw new BadRequestionException("Order date is more than 2 days old can not cancel this order !");
    }

    public int confirmOrder(Integer orderId) {
        Orderr orderUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));

        LocalDate currentDate = LocalDate.now();
        LocalDate orderDate = orderUpdate.getOrderDate();
        LocalDate deadLineDate = orderDate.plusDays(2);

        if (currentDate.isBefore(deadLineDate) || currentDate.isEqual(deadLineDate)) {
            orderUpdate.setStatus("CONFIRMED");
            return orderRepository.save(orderUpdate).getId();
        }
        throw new BadRequestionException("Order date is more than 2 days old, it was confirmed by default !");
    }

    public int createOrder(CreateOrderRequest requestDto, Integer userId) {
//        Customer customer = orderRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("Customer does not exist with this ID: " + userId));
        Orderr newOrder = Orderr.builder()
                .totalPrice(requestDto.getTotalPrice())
                .status("PENDING")
                .orderDate(LocalDate.now())
                .orderLineItemsList(requestDto.getOrderLineItemsList())
                .build();
        return orderRepository.save(newOrder).getId();
    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }
}
