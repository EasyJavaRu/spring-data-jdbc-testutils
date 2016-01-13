package ru.easyjava.spring.data.jdbc.entity;

import lombok.Data;

/**
 * Order entity.
 */
@SuppressWarnings("PMD")
@Data
public class Order {
    /**
     * ID field.
     */
    private Integer id;

    /**
     * CUSTOMER field.
     */
    private Customer customer;
}
