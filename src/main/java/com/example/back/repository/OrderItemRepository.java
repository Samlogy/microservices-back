package com.example.back.repository;

import com.example.back.model.OrderItem;
import com.example.back.model.Orderr;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Transactional
    void deleteByOrderr(Orderr orderr);
    @Transactional
    List<OrderItem> findByOrderr(Orderr orderr);
}
