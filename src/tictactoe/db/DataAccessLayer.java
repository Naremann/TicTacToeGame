/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tictactoe.AlertMessage;

/**
 *
 * @author user
 */
public class DataAccessLayer {

    private static final String SELECT_QUERY = "SELECT * FROM PLAYER WHERE USERNAME = ? and PASSWORD = ?";

    public static boolean validate(String userName, String password) {

        try {
            Connection connection = MyConnection.getConnection();
            PreparedStatement preparedStatement = null;
            if (MyConnection.isDbConnected(connection) == true) {
                preparedStatement = connection.prepareStatement(SELECT_QUERY);

                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
                preparedStatement.close();
                connection.close();

            }
        } catch (SQLException e) {
            AlertMessage.infoBox(e.getLocalizedMessage(), "Error!", null);
        }
        return false;
    }

}
