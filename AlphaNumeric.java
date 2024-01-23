package HospitalTest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class AlphaNumeric {
        private Connection connection;
        private PreparedStatement preparedStatement;

        public AlphaNumeric() throws SQLException {
            String jdbcUrl = "jdbc:postgresql://your_database_url";
            String username = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        }

        public void create() throws SQLException {
            try {
                preparedStatement = connection.prepareStatement("CREATE SEQUENCE hospital_pid_seq START 1");
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("CREATE TABLE hospital (" +
                        "pid VARCHAR(10) PRIMARY KEY," +
                        "name VARCHAR(50) NOT NULL," +
                        "age INT NOT NULL," +
                        "gender VARCHAR(10) NOT NULL," +
                        "contact BIGINT NOT NULL," +
                        "disease VARCHAR(20) NOT NULL," +
                        "doctor_name VARCHAR(20) NOT NULL," +
                        "fees DOUBLE PRECISION NOT NULL)");

                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("CREATE OR REPLACE FUNCTION set_pid()" +
                        "RETURNS TRIGGER AS $$" +
                        "BEGIN" +
                        "    NEW.pid := CONCAT('WC', NEXTVAL('hospital_pid_seq'));" +
                        "    RETURN NEW;" +
                        "END;" +
                        "$$ LANGUAGE plpgsql;");

                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("CREATE TRIGGER set_pid_trigger" +
                        "BEFORE INSERT ON hospital" +
                        "FOR EACH ROW EXECUTE FUNCTION set_pid();");

                preparedStatement.executeUpdate();

                System.out.println("Table created successfully with alphanumeric PID.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        }

        public static void main(String[] args) throws IOException, SQLException {
            AlphaNumeric tableCreator = new AlphaNumeric();
            tableCreator.create();
        }
    }
