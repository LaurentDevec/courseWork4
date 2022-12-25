package mavenCourseWork.Dao;

import mavenCourseWork.app.Alpinist;
import ru.laurent.db.base.ConnectionSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlpinistDao implements Dao<Alpinist, Integer> {

    public void createTable(){
        String create = "CREATE TABLE IF NOT EXISTS tb-alpinists(" + "author_id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "address VARCHAR(100) NOT NULL" +
                ");";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // ClassNotFoundException, если класс, переданный в качестве аргумента, не был найден
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){

            try (Statement statement = connection.createStatement()){

                statement.executeUpdate(create);
                System.out.println("Таблица была создана");
            }
        } catch (SQLException e) {
            System.out.println("Таблица не была создана " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Alpinist alpinist) {
        String insert = "INSERT INTO tb_alpinists (name, address) VALUES (?, ?) " +
                "RETURNING alpinist_id";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){
            try (PreparedStatement statement = connection.prepareStatement(insert)){
                // передача данных вместо ? знаков
                // INSERT INTO tb_authors (name, age) VALUES (?, ?)
                statement.setString(1, alpinist.getName());
                statement.setString(2, alpinist.getAddress());
                statement.setInt(3, alpinist.getAge());
                // statement.executeUpdate();
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("alpinist_id");
                    System.out.println("Данные были добавлены, идентификатор alpinist " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Alpinist alpinist) {
        String update = "UPDATE alpinist SET name = ?, address = ? " +
                "WHERE alpinist_id = ?";
    }

    @Override
    public void deleteByPK(Integer id) {
        String delete = "DELETE FROM tb_users WHERE alpinist_id = " + id;
    }

    @Override
    public Alpinist getByPK(Integer id) {
        Alpinist alpinist = null;
        String select = "SELECT name, age FROM tb_authors " +
                "WHERE author_id = ?";
        // в блоке where можно использовать операторы сравнения =, > < >= <= !=
        // в блоке where можно объединять условия через AND OR NOT
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }
        // "SELECT name, age FROM tb_authors WHERE author_id = ?";
        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                               alpinist.setId(id);
                    alpinist.setAddress(resultSet.getString("address"));
                    alpinist.setName(resultSet.getString("name"));
                    alpinist.setAge(resultSet.getInt("age"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alpinist;
    }

    @Override
    public List<Alpinist> getAll() {
        List<Alpinist> alpinists = new ArrayList<>();
        String select = "SELECT * FROM tb_authors";


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){
            try (Statement statement = connection.createStatement()){

                ResultSet resultSet = statement.executeQuery(select);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    int age = resultSet.getInt("alpinist_age");
                    int id = resultSet.getInt("alpinist_id");

                    Alpinist alpinist = new Alpinist();
                    alpinist.setId(id);
                    alpinist.setName(name);
                    alpinist.setAddress(address);
                    alpinist.setAge(age);

                    alpinists.add(alpinist);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alpinists;
    }
}