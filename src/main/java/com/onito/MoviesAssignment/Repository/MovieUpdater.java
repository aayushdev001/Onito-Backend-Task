package com.onito.MoviesAssignment.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieUpdater {

    public static void update() {
        String url = "jdbc:mysql://localhost:3306/movie";
        String username = "root";
        String password = "mysql123";

        String sql = "UPDATE Movie SET runtime_minutes = " +
                "CASE " +
                "    WHEN genres = 'Documentary' THEN runtime_minutes + 15 " +
                "    WHEN genres = 'Animation' THEN runtime_minutes + 30 " +
                "    ELSE runtime_minutes + 45 " +
                "END";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            System.out.println("Number of rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

