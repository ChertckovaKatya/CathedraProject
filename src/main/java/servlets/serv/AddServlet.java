package servlets.serv;

import cathedra.model.FileProcessing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        FileProcessing file = new FileProcessing();
        String fileName = "Test2.txt";
        try {
            Boolean result = file.parsingFile(fileName);
            if (result){
                out.println("data successfully added");
            }
            else {
                out.println("data not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
