package com.mati.domain.order;

import com.mati.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            value = "SELECT o.* " +
                    "FROM product_order o " +
                    "WHERE o.id = :order_id",
            nativeQuery = true
    )
    @Override
    Optional<Order> findById(@Param("order_id") Long id);


}
