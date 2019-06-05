package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.AllTests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OutrutTestServlet")
public class OutrutTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseHandler db = new DatabaseHandler();
        List<AllTests> tests = null;
        try {
            tests = db.getTests();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("tests",tests);
        request.getRequestDispatcher("/view_of_main_pages/AllTestsUser.jsp").forward(request, response);
    }
}
