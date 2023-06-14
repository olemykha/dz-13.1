package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DbReader {
    private final static String URL = "jdbc:postgresql://localhost:4567:5432/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "postgres";
    private final static String QUERY_SELECT_ALL = "select * from robotdreams";
    private final static String QUERY_INSERT = "insert into robotdreams values(?,?,?)";
    private final static String QUERY_UPDATE = "update robotdreams set firstname=? where id=?";
    private final static String QUERY_DELETE = "delete from robotdreams where id=?";

    public static List<TestModel> select() {
        List<TestModel> testModel = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement =  connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_ALL);

            while (resultSet.next()){
                TestModel number = new TestModel(resultSet.getInt("id"), resultSet.getString("firstname"),resultSet.getString("lastname"));
                testModel.add(number);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please, your check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return testModel;
    }

    public static void insert(int id, String firstname, String lastname) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please, your check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void update(int id, String firstname) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please, your check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void delete(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please, your check connection string" +
                    ". URL [%s], name [%s], pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }
}
