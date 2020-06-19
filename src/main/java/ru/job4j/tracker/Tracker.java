package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Переменная для обеспечения уникальности заявки
     */
    private int ids = 1;

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(ids++);
        items[position++] = item;
        return item;
    }

    /**
     * Метод получения списка всех заявок
     *
     * @return Массив ненулевых элементов Item
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /* Метод получения списка по имени
     *
     * @param key - ключ поиска (ищем по имени)
     * @return Список заявок, чьи имена совпадают с ключом
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[items.length];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                result[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /* Метод получения элемента Item по ID
     *
     * @param id идентификатор заявки
     * @return Объект класса Item, если он есть в массиве, иначе - null
     */
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
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
        items[index] = item;
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
        System.arraycopy(items, index + 1, items, index, position - index);
        items[position - 1] = null;
        position--;
        return true;
    }

    /**
     * Метод ищет индекс элемента Item в массиве items по его id
     *
     * @param id - уникальное значение для каждого элемента Item.
     * @return индекс искомого Item, если элемент с таким id есть в массиве, иначе -1
     */
    private int indexOf(int id) {
        for (int i = 0; i < position; i++) {
            if (items[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}