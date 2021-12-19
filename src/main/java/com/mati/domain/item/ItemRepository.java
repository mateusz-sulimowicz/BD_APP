package com.mati.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(
            value = "SELECT i.* " +
                    "FROM item i " +
                    "JOIN config c ON i.id = c.item_id " +
                    "WHERE c.order_id = :order_id AND c.module_id = :module_id",
            nativeQuery = true
    )
    Optional<Item> findByOrderAndModule(@Param("order_id") Long orderId, @Param("module_id") Long moduleId);


}
