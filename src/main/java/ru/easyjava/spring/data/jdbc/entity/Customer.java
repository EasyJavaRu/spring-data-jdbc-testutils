package ru.easyjava.spring.data.jdbc.entity;

import lombok.Data;

/**
 * Customer entity.
 */
@SuppressWarnings("PMD")
@Data
public class Customer {
    /**
     * ID field.
     */
    private Integer id;

    /**
     * EMAIL field.
     */
    private String email;
}
