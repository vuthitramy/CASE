package com.example.baitapsupport1.Controlls;

import com.example.baitapsupport1.Model.ConnectionJDBC;
import com.example.baitapsupport1.Model.Student;
import com.example.baitapsupport1.Model.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new ConnectionJDBC();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action){
            case "add":
                try {
                    addStudent(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String address = req.getParameter("address");

        Student student = new Student(name,age,address);
        studentDAO.add(student);

        listStudent(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                showFormAddStudent(req, resp);
                break;
            case "delete" :
                try {
                    deleteStudent(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            default:
                try {
                    listStudent(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException, ClassNotFoundException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.deleteStudent(id);
       listStudent(req,resp);
    }

    private void showFormAddStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("addStudent.jsp");
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Student> list = studentDAO.selectAllStudent();
        req.setAttribute("lists", list);
        req.getRequestDispatcher("listStudent.jsp").forward(req, resp);


    }
}
