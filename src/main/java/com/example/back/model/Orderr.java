package com.example.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orderr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL)
    private List<OrderItem> orderLineItemsList;

//    @OneToMany
//    @JoinColumn(name = "client_id")
//    private Client client;
}
