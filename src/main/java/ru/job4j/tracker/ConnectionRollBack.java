package ru.job4j.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection, which rollback all commits.
 * It is used for integration test.
 */
public class ConnectionRollBack {

    /**
     * Create connection with autocommit=false mode and rollback call, when conneciton is closed.
     *
     * @param connection connection.
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollBack.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object res = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        res = method.invoke(connection, args);
                    }
                    return res;
                }
        );
    }
}
