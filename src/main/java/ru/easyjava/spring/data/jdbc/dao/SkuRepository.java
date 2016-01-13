package ru.easyjava.spring.data.jdbc.dao;

import ru.easyjava.spring.data.jdbc.entity.Sku;

/**
 * SKU database layer.
 */
public interface SkuRepository {
    /**
     * Creates new SKU in the database.
     * @param sku SKU to craete.
     */
    void add(Sku sku);

    /**
     * Returns description of the SKU with specified id.
     * @param id SKU id value.
     * @return SKU description value.
     */
    String getDescription(Integer id);

    /**
     * Returns description of the SKU with id=3.
     * @return Third SKu description value.
     */
    String getThirdSkuDescription();
}
