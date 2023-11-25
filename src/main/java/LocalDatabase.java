import java.sql.*;

public class LocalDatabase {

    Connection connection = null;
    public void POST(String command) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            statement.executeUpdate(command);
            connection.close();
        } catch (SQLException | ClassNotFoundException c) {
            throw new RuntimeException(c);
        }
    }

    public String GET(String command, String value) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(command);
            String result = resultSet.getString(value);
            connection.close();

            return result;

        } catch (SQLException | ClassNotFoundException c) {
            throw  new RuntimeException(c);
        }
    }

    public Boolean CHECK(String command, String value) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(command);
            String result = resultSet.getString(value);
            connection.close();

            return result != null;
        } catch (SQLException | ClassNotFoundException c) {
            throw new RuntimeException(c);
        }
    }
}