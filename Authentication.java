package HospitalTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {
    Scanner ss=new Scanner(System.in);
    private Connection connection;
    private Scanner scanner;
    PreparedStatement preparedStatement;
    public Authentication(Connection connection, Scanner sc) {
        this.connection = connection;
        this.scanner = sc;
    }
    public  boolean authenticateAdmin(String username, String password) throws SQLException {
            preparedStatement = connection.prepareStatement("SELECT FROM Admin Where username = ? AND password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
    }
        public  boolean authenticateUser(String username, String password) throws SQLException {
            preparedStatement = connection.prepareStatement("SELECT FROM Patient Where username = ? AND password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }
