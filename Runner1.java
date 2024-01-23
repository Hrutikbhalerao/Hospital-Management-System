package HospitalTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class Runner1 {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner sc = new Scanner(System.in);
        Scanner ss = new Scanner(System.in);
        String str = "y";
        int choice;
        Properties prop = new Properties();
        prop.load(new FileInputStream("connection.properties"));
        Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        PatientController1 patientController1 = new PatientController1(conn, sc);
        AdminUserCreation adminUserCreation = new AdminUserCreation(conn, sc);
        Authentication authentication = new Authentication(conn, sc);
//        patientController1.create(); //For creating table
//        adminUserCreation.tableAdmin(); //For creating Admin table to store admin account details
//        adminUserCreation.createAdminAccount(); //Creating new admin account
//        adminUserCreation.tablePatient(); For creating Patient table to store Patient account details
//        adminUserCreation.createPatientAccount(); //Creating new patient account
        do {
            int ch;
            System.out.println("Enter 1 to login as Admin: ");
            System.out.println("Enter 2 to Sign up/Login  as Patient: ");
            ch = sc.nextInt();
            if (ch == 1) { //Admin

                String username, password;
                System.out.println("Enter username :");
                username = ss.next();
                System.out.println("Enter password :");
                password = ss.next();
                if (authentication.authenticateAdmin(username, password)) {
                    System.out.println("Login Successfully! ");
                    do {
                        System.out.println("Enter your choice :");
                        System.out.println("Enter 1 to create patient: ");
                        System.out.println("Enter 2 for finding doctor name : ");
                        System.out.println("Enter 3 for changing doctor name : ");
                        System.out.println("Enter 4 to find patient from patient name : ");
                        System.out.println("Enter 5 to find patient of same disease : ");
                        System.out.println("Enter 6 for giving discount to patient: ");
                        System.out.println("Enter 7 for displaying all patient data: ");
                        System.out.println("Enter 8 to delete specific patient data: ");
                        System.out.println("Enter 9 to delete all data of patient: ");
                        System.out.println("Enter 10 for Exit");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {
                                patientController1.createPatient();
                            }
                            case 2 -> {
                                patientController1.doctorName();
                            }
                            case 3 -> {
                                patientController1.changeDoctorName();
                            }
                            case 4 -> {
                                patientController1.findPatient();
                            }
                            case 5 -> {
                                patientController1.sameDisease();
                            }
                            case 6 -> {
                                patientController1.discount();
                            }
                            case 7 -> {
                                patientController1.allPatients();
                            }
                            case 8->{
                                patientController1.deletePatient();
                            }
                            case 9->{
                                System.out.println("Are you sure to delete all patient data: Press Y for Yes");
                                String st=ss.next();
                                if(Objects.equals(st, "y") || Objects.equals(st, "Y"))
                                    patientController1.deleteAllData();
                            }
                            case 10->{
                                return;
                            }
                            default -> {
                                System.out.println("Please enter correct choice: ");
                            }
                        }
                        System.out.println("Do you want to continue as an admin again? Press Y for Yes");
                        str = ss.next();
                    } while (Objects.equals(str, "y") || Objects.equals(str, "Y"));
                } else {
                    System.out.println("Invalid Credentials! ! ! Login Failed");
                }
            }
            else if (ch == 2) { //patient
                System.out.println("Enter 1 for Login\nEnter 2 for Sign Up");
                int n=sc.nextInt();
                if(n==1) {
                    String username, password;
                    System.out.println("Enter username :");
                    username = ss.next();
                    System.out.println("Enter password :");
                    password = ss.next();
                    if (authentication.authenticateUser(username, password)) {
                        System.out.println("Login Successfully");
                        System.out.println("Enter your choice: ");
                        System.out.println("Enter 1 to fill your details: ");
                        System.out.println("Enter 2 for changing your doctor : ");
                        System.out.println("Enter 3 for see your details : ");
                        System.out.println("Enter 4 for exit : ");
                        int patientChoice1=sc.nextInt();
                        switch (patientChoice1){
                            case 1->{
                                patientController1.createPatient();
                            }
                            case 2->{
                                patientController1.changeDoctorName();
                            }
                            case 3->{
                                patientController1.findPatient();
                            }
                            case 4->{
                                return;
                            }
                            default -> {
                                System.out.println("Please enter correct choice ! !");
                            }
//
                        }

                    }
                    else {
                        System.out.println("No account found ! ! !");
                        System.out.println("Do you want to create new Patient account? Y for Yes ");
                        String str1 = ss.next();
                        if (Objects.equals(str1, "y") || str.equals("Y"))
                            adminUserCreation.createPatientAccount();
                    }
                }
                else if(n==2){
                    adminUserCreation.createPatientAccount();
                }
                else{
                    System.out.println("Invalid choice: ");
                }
            } else {
                System.out.println("Invalid choice: ");
            }

            System.out.println("Do you want to continue login again? Press Y for Yes: ");
            str = ss.next();
        } while (Objects.equals(str, "y") || Objects.equals(str, "Y"));
    }
}