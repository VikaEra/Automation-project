package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import lombok.SneakyThrows;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getCardPayment() {
        var connection = getConn();
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(connection, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditPayment() {
        var connection = getConn();
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(connection, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}