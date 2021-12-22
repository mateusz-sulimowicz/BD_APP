package com.mati.domain.order;

import com.mati.domain.item.Item;
import com.mati.domain.item.ItemDAO;
import com.mati.domain.option.Option;
import com.mati.domain.product.Product;
import com.mati.domain.product.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderRowMapper implements RowMapper<Order> {

    ProductDAO productDAO;

    @Autowired
    public OrderRowMapper(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderDate(rs.getDate("order_date"));
        order.setValue(rs.getBigDecimal("value"));
        order.setId(rs.getLong("id"));
        order.setQuantity(rs.getLong("quantity"));

        Product product = productDAO
                .findById(rs.getLong("product_id"))
                .orElseThrow(RuntimeException::new);
        ;

        order.setProduct(product);
        return order;
    }
}
