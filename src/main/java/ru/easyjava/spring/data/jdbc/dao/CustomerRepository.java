package ru.easyjava.spring.data.jdbc.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Customer database layer.
 */
public interface CustomerRepository {
    /**
     * Creates new customer using only e-mail value.
     * @param email New customer's e-mail.
     * @return inerted item id.
     */
    Number add(String email);

    /**
     * Gets customer's email by it's id.
     * @param id id to query.
     * @return email value or null.
     */
    String getEmail(Long id);

    /**
     * Gets all emails from the database.
     * @return Collection of customer's emails.
     */
    Collection<String> allEmails();

    /**
     * Retrieves all the customer data from the database.
     * @return Collection of key-value customers data.
     */
    List<Map<String, Object>> all();
}
