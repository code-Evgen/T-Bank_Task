package ru.tatarinov.t_bank_task.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest
@Sql(scripts = {"classpath:create_test_table.sql"}, executionPhase = BEFORE_TEST_CLASS)
class RequestRepositoryTest {
    @Autowired
    private Connection connection;
    @Autowired
    private RequestRepository requestRepository;

    @Value("${database.test.table}")
    private String databaseTable;


    @Test
    void testSave() {
        String sql = "INSERT INTO " + databaseTable + " (ip, input_text, translated_text) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "127.0.0.1");
            statement.setString(2, "hello world");
            statement.setString(3, "привет мир");
            statement.executeUpdate();


            String sql2 = "SELECT COUNT(*) FROM " + databaseTable + " WHERE ip = ? AND input_text = ? AND translated_text = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, "127.0.0.1");
            statement2.setString(2, "hello world");
            statement2.setString(3, "привет мир");
            ResultSet resultSet = statement2.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            assertEquals(1, count);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}