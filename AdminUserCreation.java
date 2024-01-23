package HospitalTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminUserCreation {
    Scanner ss=new Scanner(System.in);
    private Connection connection;
    private Scanner scanner;
    PreparedStatement preparedStatement;
    public AdminUserCreation(Connection connection, Scanner sc) {
        this.connection = connection;
        this.scanner = sc;
    }
    public void tableAdmin() throws SQLException {
        preparedStatement = connection.prepareStatement("create table Admin(admin_id SERIAL PRIMARY KEY, username varchar(50)UNIQUE NOT NULL, password varchar(20)NOT NULL);");
        preparedStatement.executeUpdate();

    }
    public void tablePatient() throws SQLException {
        preparedStatement = connection.prepareStatement("create table Patient(admin_id SERIAL PRIMARY KEY, username varchar(50)UNIQUE NOT NULL, password varchar(20)NOT NULL);");
        preparedStatement.executeUpdate();
    }
    public void createAdminAccount() throws SQLException {
        String username,password;
        System.out.println("Enter admin username to create :");
        username=ss.next();
        System.out.println("Enter admin password to create :");
        password=ss.next();
        preparedStatement = connection.prepareStatement("Insert into Admin (username, password) values (?, ?);");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        preparedStatement.executeUpdate();
    }
    public void createPatientAccount() throws SQLException {
            String username, password;
            System.out.println("Enter patient username to create :");
            username = ss.next();
            System.out.println("Enter patient password to create :");
            password = ss.next();
            preparedStatement = connection.prepareStatement("Insert into Patient (username, password) values (?, ?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }
}

