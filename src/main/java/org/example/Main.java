package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydatabase",
                    "myuser",
                    "mypassword"
            );

            // Step 2: Create a statement object
            stmt = conn.createStatement();

            // Step 3: Create a new table
            String createTableSql = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "email VARCHAR(255)" +
                    ")";
            stmt.executeUpdate(createTableSql);

            // Step 4: Insert some data into the table
            String insertSql = "INSERT INTO customers (name, email) VALUES " +
                    "('Alice', 'alice@example.com'), " +
                    "('Bob', 'bob@example.com'), " +
                    "('Charlie', 'charlie@example.com')";
            stmt.executeUpdate(insertSql);

            // Step 5: Retrieve data from the table
            String selectSql = "SELECT * FROM customers";
            rs = stmt.executeQuery(selectSql);

            // Step 6: Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("id = " + id + ", name = " + name + ", email = " + email);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Step 7: Close the resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}