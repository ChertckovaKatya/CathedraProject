package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreatTestServlet")
public class CreatTestServlet extends HttpServlet {
    Integer quantity_questions = null;
    Integer i =0;
    ArrayList<Integer> idTypeQuestions = new ArrayList<>();
    List <Test> quest_answer = null;
    List<Integer> idQuestions = new ArrayList<>();
    Integer idTest = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("first") || type.equals("third")) {
            String answer = request.getParameter("answer");
            System.out.println("answer=" + answer + " , " + "type=" + type);
        }
        if (type.equals("second")){
            String[] answers =  request.getParameterValues("answer");
            System.out.print("answers: ");
            if (answers!=null) {
                for (int i = 0; i < answers.length; i++) {
                    System.out.print(answers[i]);
                }
            }
        }
       i++;
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idTest = Integer.valueOf(request.getParameter("idTest"));
        System.out.println("idTest = "+idTest);
        DatabaseHandler db = new DatabaseHandler();
        try {
            quantity_questions = db.getQuentityQuestions(idTest);
            idTypeQuestions = db.getTypeQuestions(idTest);
            idQuestions = db.getidQuestions(idTest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (quantity_questions!=null && idTypeQuestions!=null && idQuestions!=null){
        if ( i < quantity_questions) {
            request.setAttribute("id", idTypeQuestions.get(i));
            try {
                quest_answer = db.getQuestAnswer(idTest,idQuestions.get(i));
//                System.out.println(quest_answer.toString());
                request.setAttribute("result",quest_answer);
                request.setAttribute("idTest",idTest);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/form_test/TestDemonstration.jsp").forward(request, response);
        }
        else {
            request.setAttribute("end","end");
            request.getRequestDispatcher("/form_test/TestDemonstration.jsp").forward(request, response);
        }}
        else {
            System.out.println("Неизвестно количество вопросов или неизвестны типы вопросов");
        }
    }
}
