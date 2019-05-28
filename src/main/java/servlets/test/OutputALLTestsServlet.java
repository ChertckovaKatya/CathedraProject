package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.AllTests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OutputALLTestsServlet")
public class OutputALLTestsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseHandler db = new DatabaseHandler();
        List<AllTests> tests = null;
        try {
            tests = db.getTests();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("tests",tests);
        request.getRequestDispatcher("/form_test/AllTests.jsp").forward(request, response);
    }
}
