package HospitalTest;

import java.io.IOException;
import java.security.spec.ECField;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PatientController1 {
    private Connection connection;
    private Scanner scanner;
    PreparedStatement preparedStatement;

    public PatientController1(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    Scanner ss = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);

    public void create() throws IOException, SQLException {
        preparedStatement = connection.prepareStatement("CREATE TABLE hospital (" +
                "pid SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT NOT NULL," +
                "gender VARCHAR(10) NOT NULL," +
                "contact BIGINT NOT NULL," +
                "disease VARCHAR(20) NOT NULL," +
                "doctor_name VARCHAR(20) NOT NULL," +
                "fees DOUBLE PRECISION NOT NULL)");
        preparedStatement.executeUpdate();
    }

    public void createPatient() throws SQLException {
        String patientName, disease, doctorName, gender;
        double fees;
        System.out.println("Enter patient name: ");
        patientName = ss.nextLine();
        System.out.println("Enter patient age: ");
        int age = sc.nextInt();
        System.out.println("Enter patient gender: ");
        gender = ss.nextLine();
        System.out.println("Enter Patient contact number: ");
        long contact = sc.nextLong();
        System.out.println("Enter patient disease: ");
        disease = ss.nextLine();
        System.out.println("Enter doctor name: ");
        doctorName = ss.next();
        System.out.println("Enter doctor fees: ");
        fees = sc.nextDouble();
        Patient1 patient1 = new Patient1(patientName, age, gender, contact, disease, doctorName, fees);
        preparedStatement = connection.prepareStatement("insert into hospital (name, age, gender, contact, disease,  doctor_name, fees) values (?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, patient1.getName());
        preparedStatement.setInt(2, patient1.getAge());
        preparedStatement.setString(3, patient1.getGender());
        preparedStatement.setLong(4, patient1.getContact());
        preparedStatement.setString(5, patient1.getDisease());
        preparedStatement.setString(6, patient1.getDoctorName());
        preparedStatement.setDouble(7, patient1.getFees());
        preparedStatement.executeUpdate();
        System.out.println("Patient " + patientName + " created successfully ");
        System.out.println("Patient details you entered: ");
        System.out.println(patient1.toString());
    }

    public void doctorName() throws IOException, SQLException {
        System.out.println("Enter doctor name: ");
        Patient1 patient1 = new Patient1(ss.next());
        preparedStatement = connection.prepareStatement("select * from hospital where doctor_name = ?");
        preparedStatement.setString(1, patient1.getDoctorName());
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Patient ID |  Name  | Age | Gender | Contact | Disease | Doctor Name | Fees");
        boolean flag=false;
        while(resultSet.next()) {
            System.out.println("     " + resultSet.getInt("pid") + "     " + resultSet.getString("name") +
                    " " + resultSet.getInt("age") + "      " + resultSet.getString("gender") + "    " +
                    resultSet.getString("contact") + "    " + resultSet.getString("disease") + "    "
                    + resultSet.getString("doctor_name") + "   " + resultSet.getDouble("fees"));
//            System.out.println("Data retrieved successfully for doctor " + patient1.getDoctorName());
            flag=true;
        }
        if(!flag)
            System.out.println("Invalid doctor name ");
    }

    public void changeDoctorName() {
        try {
            int pid;
            System.out.println("Enter patient id whose doctor needs to change: ");
            pid = sc.nextInt();
            System.out.println("Which doctor are you referring: ");
            Patient1 patient1 = new Patient1(ss.next());
            preparedStatement = connection.prepareStatement("update hospital set doctor_name = ? where pid=?;");
            preparedStatement.setString(1, patient1.getDoctorName());
            preparedStatement.setInt(2, pid);
            preparedStatement.executeUpdate();
            System.out.println("Doctor changes successfully");
        } catch (Exception e) {
            System.out.println("No patient id found ! ! !");
        }
    }

    public void findPatient() throws SQLException {
        Patient1 patient1 = new Patient1();
        System.out.println("Enter patient name to find: ");
        patient1.setName(ss.next());
        preparedStatement = connection.prepareStatement("Select * from hospital where name=?;");
        preparedStatement.setString(1, patient1.getName());
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Patient ID |  Name  | Age | Gender | Contact | Disease | Doctor Name | Fees");
        if (resultSet.next()) {
            System.out.println("     " + resultSet.getInt("pid") + "    " + resultSet.getString("name") +
                    "        " + resultSet.getInt("age") + "      " + resultSet.getString("gender") + "     " +
                    resultSet.getString("contact") +"  "+ resultSet.getString("disease") + "    " +
                    "      " + resultSet.getString("doctor_name") + "     " + resultSet.getDouble("fees"));
            System.out.println("Patient " + patient1.getName() + " founded successfully");
        } else
            System.out.println("No patient found of name " + patient1.getName());
    }

    public void sameDisease() throws SQLException {
        Patient1 patient1 = new Patient1();
        System.out.println("Enter the disease: ");
        patient1.setDisease(ss.next());
        preparedStatement = connection.prepareStatement("Select * from hospital where disease=?;");
        preparedStatement.setString(1, patient1.getDisease());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean flag = false;
        System.out.println("Patient ID |  Name  | Age | Gender | Contact | Disease | Doctor Name | Fees");
        while (resultSet.next()) {
            System.out.println("     " + resultSet.getInt("pid") + "      " + resultSet.getString("name") +
                    "        " + resultSet.getInt("age") + "       " + resultSet.getString("gender") + "      " +
                    resultSet.getString("contact") +"    "+ resultSet.getString("disease") + "   " +"     " + resultSet.getString("doctor_name")
                    + "      " + resultSet.getDouble("fees"));
            flag = true;
        }
        if (!flag)
            System.out.println("No patient found of disease " + patient1.getDisease());
    }

    public void discount() throws SQLException {
        Patient1 patient1 = new Patient1();
        double fees = 0;
        System.out.println("Enter patient id to whom discount to offer: ");
        int pid = sc.nextInt();
        try {
            preparedStatement = connection.prepareStatement("select fees from hospital where pid=?;");
            preparedStatement.setInt(1, pid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fees = resultSet.getDouble("fees");
            }
        } catch (Exception e) {
            System.out.println("Invalid patient id: ");
        }
//        System.out.println(fees);
        System.out.println("Enter the discount amount: ");
        double discount = sc.nextDouble();
        if (fees < discount) {
            System.out.println("Please enter discount amount more than fees ! ! !");
            return;
        }
        preparedStatement = connection.prepareStatement("update hospital set fees=fees-? where pid=?;");
        preparedStatement.setDouble(1, discount);
        preparedStatement.setInt(2, pid);
        preparedStatement.executeUpdate();
        System.out.println("Discount of Rs. " + discount + " offered to patient id: " + pid);


    }

    public void allPatients() throws SQLException {
        preparedStatement = connection.prepareStatement("Select * from hospital");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Patient ID |  Name  | Age | Gender | Contact | Disease | Doctor Name | Fees");
        while (resultSet.next()) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("     " + resultSet.getInt("pid") + "     | " + resultSet.getString("name") + " | " +
                    resultSet.getInt("age") + " | " +
                    resultSet.getString("gender") + " | " +
                    resultSet.getString("contact") + " | " +
                    resultSet.getString("disease") + " | " +
                    resultSet.getString("doctor_name") + " | " +
                    resultSet.getDouble("fees"));

        }
    }
    public void deletePatient() throws SQLException {
            System.out.println("Enter patient id to delete it's data : ");
            int pid=sc.nextInt();
            preparedStatement = connection.prepareStatement("Delete from hospital WHERE pid=?");
            preparedStatement.setInt(1,pid);
            preparedStatement.executeUpdate();
            System.out.println("Patient details deleted successfully");

    }
    public void deleteAllData() {
        try {
            preparedStatement = connection.prepareStatement("Truncate table hospital");
            preparedStatement.executeUpdate();
            System.out.println("Table truncated successfully !");
        } catch (Exception e) {
            System.out.println("Error ! "+e.getMessage());
        }
    }
}