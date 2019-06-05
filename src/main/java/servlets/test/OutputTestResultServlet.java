package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.ResultTests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OutputTestResultServlet")
public class OutputTestResultServlet extends HttpServlet {

    Integer idUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseHandler db = new DatabaseHandler();
        List <ResultTests> list = null;
        final HttpSession session = request.getSession();

        try {
            idUser = db.getIdStudents(session.getAttribute("login"),session.getAttribute("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (idUser!=null) {
            try {
                list = db.getInfResultsTests(idUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            request.setAttribute("res",list);
        request.getRequestDispatcher("/view_of_main_pages/outputTestResults.jsp").forward(request, response);
    }
    }
}
