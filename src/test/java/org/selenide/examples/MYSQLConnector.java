package org.selenide.examples;

import com.mysql.jdbc.PreparedStatement;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnector {

    private static Connection conn;

    @Test
    public void startWebDriver()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/main_db","my_db","testest");

        }
        catch (SQLException ex)
        {
            // handle any errors
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }


    }

    public void deleteUserFromDB(String userMail) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) conn.prepareStatement("DELETE FROM `users`\n" +
                    "WHERE\n" +
                    "\t`email` = '?';");
            statement.setString(1, userMail);
        } catch (SQLException e)
        {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
