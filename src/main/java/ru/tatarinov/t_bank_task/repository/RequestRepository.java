package ru.tatarinov.t_bank_task.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.tatarinov.t_bank_task.model.RequestEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class RequestRepository {
    private final Connection connection;

    @Value("${database.table}")
    private String databaseTable;

    public RequestRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(RequestEntry requestEntry) {
        String sql = "INSERT INTO " + databaseTable + " (ip, input_text, translated_text) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, requestEntry.getIp());
            statement.setString(2, requestEntry.getInputText());
            statement.setString(3, requestEntry.getTranslatedText());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
