package ru.easyjava.spring.data.jdbc.entity;

import lombok.Data;

/**
 * SKU entity.
 */
@SuppressWarnings("PMD")
@Data
public class Sku {
    /**
     * ID field.
     */
    private Integer id;
    /**
     * DESCRIPTION field.
     */
    private String description;
}
