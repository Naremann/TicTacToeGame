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
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.AlertMessage;
import tictactoe.dto.DTOPlayer;

/**
 *
 * @author user
 */
public class DataAccessLayer {

    private static final String SELECT_QUERY = "SELECT * FROM PLAYER WHERE USERNAME = ? and PASSWORD = ?";
    private static final String INSERT_QUERY = "INSERT INTO PLAYER(USERNAME,EMAIL,PASSWORD) VALUES (?,?,?)";

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

    public static int register(DTOPlayer player) {
        Connection connection = MyConnection.getConnection();
        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            if (MyConnection.isDbConnected(connection)) {
                if(player.getUserName()!=null && player.getPassword()!=null
                        && player.getEmail()!=null){
                preparedStatement = connection.prepareStatement(INSERT_QUERY);
                preparedStatement.setString(1, player.getUserName());
                preparedStatement.setString(2, player.getEmail());
                preparedStatement.setString(3, player.getPassword());

                result = preparedStatement.executeUpdate();
                }
                preparedStatement.close();
                connection.close();

            }
        } catch (SQLException ex) {
            AlertMessage.infoBox(ex.getLocalizedMessage(), "Error!", null);

        }

        return result;
    }

}
