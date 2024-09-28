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
        return DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.username"), System.getProperty("db.password"));
    }

    @SneakyThrows
    public static String getCardPayment() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    public static String getCreditPayment() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    private static String getStatus(String query) {
        var runner = new QueryRunner();
        var conn = getConn();
        String status = runner.query(conn, query, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static void cleanTablePayment() {
        var deletePaymentEntity = "DELETE FROM payment_entity ";
        var runner = new QueryRunner();
        try (var conn = getConn()) {
            runner.update(conn, deletePaymentEntity);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @SneakyThrows
    public static void cleanTableCredit() {
        var deleteCreditEntity = "DELETE FROM credit_request_entity";
        var runner = new QueryRunner();
        try (var conn = getConn()) {
            runner.update(conn, deleteCreditEntity);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
