package com.example.baitapsupport1.Model;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    public List<Student> selectAllStudent() throws SQLException;

    public void add(Student student);

    public boolean deleteStudent(int id) throws SQLException;
}
