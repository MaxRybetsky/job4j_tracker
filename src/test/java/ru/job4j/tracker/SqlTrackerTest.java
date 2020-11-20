package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddItem() {
        try (Store tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item = tracker.add(new Item("name"));
            assertThat(tracker.findById(item.getId()), is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItem() {
        try (Store tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item = tracker.add(new Item("name"));
            assertThat(tracker.delete(item.getId()), is(true));
            assertNull(tracker.findById(item.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUpdateItem() {
        try (Store tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item = tracker.add(new Item("name"));
            Item newItem = new Item("other");
            assertThat(tracker.replace(item.getId(), newItem), is(true));
            assertThat(tracker.findById(item.getId()).getName(),
                    is(newItem.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGetItemById() {
        try (Store tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item = tracker.add(new Item("name"));
            assertThat(tracker.findById(item.getId()), is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGetItemsByName() {
        try (Store tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item1 = tracker.add(new Item("name"));
            Item item2 = tracker.add(new Item("name"));
            assertThat(tracker.findByName(item1.getName()),
                    is(List.of(item1, item2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}