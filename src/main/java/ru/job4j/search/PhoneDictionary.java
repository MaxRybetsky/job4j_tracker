package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подошедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> combineByName = (Person person) -> person.getName().contains(key);
        Predicate<Person> combineBySurname = (Person person) -> person.getSurname().contains(key);
        Predicate<Person> combineByPhone = (Person person) -> person.getPhone().contains(key);
        Predicate<Person> combineByAddress = (Person person) -> person.getAddress().contains(key);
        Predicate<Person> combine = (Person person) -> combineByName.test(person)
                || combineBySurname.test(person)
                || combineByPhone.test(person)
                || combineByAddress.test(person);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}