package com.mati.domain.item;

import com.mati.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(
            value = "SELECT i.* " +
                    "FROM " + Item.TABLE_NAME + " i " +
                    "JOIN " + Order.TABLE_NAME + " c ON i.id = c.item_id" +
                    "WHERE c.order_id = :order_id AND c.module_id = :module_id",
            nativeQuery = true
    )
    Item findByOrderAndModule(@Param("order_id") Long orderId, @Param("module_id") Long moduleId);


}
