/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package testingmysqljdbc;

/**
 *
 * @author ilazrig
 */

//=======================================================================

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingMySQLJDBC {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbk_university"; // Change to your MySQL database URL
    private static final String DB_USER = "testNoRole";
    private static final String DB_PASSWORD = "Easy1234";

    public static void main(String[] args) {
        String substring = "B%"; // Example: The substring you want to search for
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM student WHERE name LIKE ? and tot_cred<? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, substring); // Search for names starting with the given substring
            preparedStatement.setInt(2, 90);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentID = resultSet.getString("ID");
                String studentName = resultSet.getString("name");
                String DeptName = resultSet.getString("Dept_name");
                int totCredits =  resultSet.getInt("tot_cred");
                System.out.println(" Student ID: "+ studentID+"\n Student Name: " + studentName + "\n Dept Name: "
                        + DeptName +"\n Tot Credits: "+ totCredits);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


