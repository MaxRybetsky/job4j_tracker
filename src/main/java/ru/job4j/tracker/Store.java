package ru.job4j.tracker;

import java.util.List;

public interface Store extends AutoCloseable {
    /**
     * Storage initialization method
     */
    void init();

    /**
     * Adds item to storage
     *
     * @param item The item to add
     * @return Added item
     */
    Item add(Item item);

    /**
     * Replace item with specify id to
     * another specify item
     *
     * @param id   Id of item to replace
     * @param item New item
     * @return {@code true} if process of replacing
     * was successfully ended, otherwise - {@code false}
     */
    boolean replace(int id, Item item);

    /**
     * Deletes item with specify id
     *
     * @param id Id of item to delete
     * @return {@code true} if process of deleting
     * was successfully ended, otherwise - {@code false}
     */
    boolean delete(int id);

    /**
     * Searches all element in the storage
     *
     * @return The list of all elements in the storage
     */
    List<Item> findAll();

    /**
     * Searches items with the specify name
     * (also named as key)
     *
     * @param key Keyword to find items
     * @return The list if items with name
     * equals to current keyword
     */
    List<Item> findByName(String key);

    /**
     * Searches item with the specify id
     * value
     *
     * @param id Id of item to find
     * @return Item object with id value
     * as the current one, otherwise
     * returns {@code null}
     */
    Item findById(int id);
}