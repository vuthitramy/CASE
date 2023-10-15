package com.example.baitapsupport1.Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ConnectionJDBC implements StudentDAO {

    private String hostName = "localhost:3306";
    private String databaseName = "QUANLYSINHVIEN";
    private String username = "root";
    private String password = "password";

    String url = "jdbc:mysql://localhost:3306/QUANLYSINHVIEN";

    private String connectionURL = "jdbc:mysql://" + hostName + "/" + databaseName;

    public ConnectionJDBC() {
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private static final String SELECT_ALL_STUDENTS = "select * from Student";
    private static final String INSERT_STUDENT_SQL = "INSERT INTO Student (name, age, address) VALUES (?, ?, ?);";
    private static final String Delete_Student = "delete from Student where id = ?;";


    public List<Student> selectAllStudent() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS);


        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            students.add(new Student(id, name, age, address));

        }
        return students;

    }

        @Override
        public void add (Student student){
            Connection connection = getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);

                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setString(3, student.getAddress());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDelete = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Delete_Student);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowDelete;
    }
}