import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> GETLIST(String command, String columnName) {
        List<String> columnData = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(command);

            while (resultSet.next()) {
                String value = resultSet.getString(columnName);
                columnData.add(value);
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return columnData;
    }

}