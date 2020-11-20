package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    /**
     * Connection with database
     */
    private Connection cn;

    /**
     * Name of storage table
     */
    private final String tableName = "items";

    /**
     * Shows if the table already exists in database
     */
    private boolean hasTable = false;

    /**
     * Construct new object for working with storage
     * table in databases. Invoke {@link SqlTracker#init()}
     * to set connection with database
     */
    public SqlTracker() {
        init();
    }

    /**
     * Construct new tracker with given connection.
     *
     * @param cn Connection to use
     * @throws SQLException if there were problems with
     *                      connection to data base
     */
    public SqlTracker(Connection cn) throws SQLException {
        this.cn = cn;
        setTable();
    }

    /**
     * Initializes ne connection with databases. Data for
     * login are taken from property file.
     */
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            setTable();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Closes this resource
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * Adds item to storage and assign
     * new {@code id} value from storage
     * to the item
     *
     * @param item The item to add
     * @return item with new {@code id}
     * value
     */
    @Override
    public Item add(Item item) {
        if (cn != null) {
            try (final PreparedStatement statement =
                         this.cn.prepareStatement(
                                 "insert into " + tableName
                                         + " (name) values (?)",
                                 Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, item.getName());
                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        item.setId(keys.getInt(1));
                        return item;
                    }
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
        throw new IllegalStateException("Could not create new user");
    }

    /**
     * Replace item with specify id to
     * another specify item
     *
     * @param id   Id of item to replace
     * @param item New item
     * @return {@code true} if process of replacing
     * was successfully ended, otherwise - {@code false}
     */
    @Override
    public boolean replace(int id, Item item) {
        String query = "update "
                + tableName
                + " set name='"
                + item.getName()
                + "' where id="
                + id + ";";
        return editCommandsSender(query);
    }

    /**
     * Deletes item with specify id
     *
     * @param id Id of item to delete
     * @return {@code true} if process of deleting
     * was successfully ended, otherwise - {@code false}
     */
    @Override
    public boolean delete(int id) {
        String query = "delete from "
                + tableName
                + " where id=" + id;
        return editCommandsSender(query);
    }

    /**
     * Searches all element in the storage
     *
     * @return The list of all elements in the storage
     */
    @Override
    public List<Item> findAll() {
        String query = "select * from " + tableName;
        return getCommandsSender(query);
    }

    /**
     * Searches items with the specify name
     * (also named as key)
     *
     * @param key Keyword to find items
     * @return The list if items with name
     * equals to current keyword
     */
    @Override
    public List<Item> findByName(String key) {
        String query = "select * from " + tableName
                + " where name='" + key + "'";
        return getCommandsSender(query);
    }

    /**
     * Searches item with the specify id
     * value
     *
     * @param id Id of item to find
     * @return Item object with id value
     * as the current one, otherwise
     * returns {@code null}
     */
    @Override
    public Item findById(int id) {
        String query = "select * from " + tableName
                + " where id=" + id;
        List<Item> items = getCommandsSender(query);
        return items.isEmpty() ? null : items.get(0);
    }

    /**
     * Checks if storage table exists and writes
     * its info to {@code hasTable} variable. Creates
     * nwe table if it doesn't exist
     *
     * @throws SQLException if there were problems with
     *                      connection to data base
     */
    private void setTable() throws SQLException {
        if (!hasTable) {
            try (ResultSet resultSet = cn.getMetaData().
                    getTables(null,
                            null,
                            tableName,
                            null)) {
                if (resultSet.next()) {
                    hasTable = true;
                } else {
                    createTable();
                }
            }
        }
    }

    /**
     * Gets list of items that satisfy the query
     * condition
     *
     * @param query To get info from data base
     * @return List of Items
     */
    private List<Item> getCommandsSender(String query) {
        List<Item> result = new ArrayList<>();
        if (cn != null && hasTable) {
            try (Statement stat = cn.createStatement()) {
                ResultSet resultSet = stat.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    result.add(new Item(id, name));
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return result;
    }

    /**
     * Edits (updates and deletes) info of items
     * int storage with specify query
     *
     * @param query Edition task
     * @return {@code true} if some info in storage was
     * changed, otherwise - {@code false}
     */
    private boolean editCommandsSender(String query) {
        if (cn != null) {
            try (Statement stat = cn.createStatement()) {
                int res = stat.executeUpdate(query);
                if (res != 0) {
                    return true;
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return false;
    }

    /**
     * Checks if need to create new table in storage
     */
    private void createTable() {
        if (!hasTable) {
            try (Statement stat = cn.createStatement()) {
                String creating = "create table "
                        + tableName
                        + "(id serial primary key, name text);";
                stat.executeUpdate(creating);
                hasTable = true;
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }
}