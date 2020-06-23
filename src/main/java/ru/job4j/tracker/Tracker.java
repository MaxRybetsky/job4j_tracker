package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Переменная для обеспечения уникальности заявки
     */
    private int ids = 1;

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        items.add(item);
        item.setId(ids);
        ids++;
        return item;
    }

    /**
     * Метод получения списка всех заявок
     *
     * @return Массив ненулевых элементов Item
     */
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /* Метод получения списка по имени
     *
     * @param key - ключ поиска (ищем по имени)
     * @return Список заявок, чьи имена совпадают с ключом
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /* Метод получения элемента Item по ID
     *
     * @param id идентификатор заявки
     * @return Объект класса Item, если он есть в массиве, иначе - null
     */
    public Item findById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Метод находит заявку по id, удаляет ее и заменяет на новую
     *
     * @param id   идентификатор заявки, которую надо удалить
     * @param item заявка, на которую необходимо заменить исходную заявку
     * @return true - если завка была успешно заменена, иначе - false
     */
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        item.setId(id);
        items.set(index, item);
        return true;
    }

    /**
     * Метод удаляет заявку по ее id
     *
     * @param id идентификатор заявки
     * @return true - если удаление прошло успешно, иначе - false
     */
    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items.remove(index);
        return true;
    }

    /**
     * Метод ищет индекс элемента Item в массиве items по его id
     *
     * @param id - уникальное значение для каждого элемента Item.
     * @return индекс искомого Item, если элемент с таким id есть в массиве, иначе -1
     */
    private int indexOf(int id) {
        int index = 0;
        for (Item item : items) {
            if (item.getId() == id) {
                return index;
            }
            index++;
        }
        return -1;
    }
}