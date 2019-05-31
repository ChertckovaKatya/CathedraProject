package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreatTestServlet")
public class CreatTestServlet extends HttpServlet {
    private Integer quantity_questions = null;
    private int i = 0;
    private ArrayList<Integer> idTypeQuestions = new ArrayList<>();
    private List<Integer> idQuestions = new ArrayList<>();
    private Integer idTest = null;
    private List<Test> quest_answer;
    private DatabaseHandler db = new DatabaseHandler();
    private List<PassedTest> results = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer type = Integer.valueOf(request.getParameter("type"));
        if (type == 3) {
            String answer = request.getParameter("answer");
            System.out.println("answer=" + answer + " , " + "type=" + type);
            results.add(new PassedTest(type,answer));
        }
        if (type == 1 || type == 2){
            String[] answers =  request.getParameterValues("answer");
            System.out.print("answers: ");
            results.add(new PassedTest(type,answers));
            if (answers!=null) {
                for (int i = 0; i < answers.length; i++) {
                    System.out.print(answers[i]);
                }
            }
        }
       i++;
        System.out.println(results.toString());
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idTest = Integer.valueOf(request.getParameter("idTest"));
        System.out.println("idTest = "+idTest);
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
                    request.setAttribute("idQuestion",idQuestions.get(i));
                   request.setAttribute("result",quest_answer);
                    request.setAttribute("idTest",idTest);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/form_test/TestDemonstration.jsp").forward(request, response);
            }
            else {
                final HttpSession session = request.getSession();

                Integer idUser = null;
                Integer resaddTest = null;
                try {
                    idUser = db.getIdStudents(session.getAttribute("login"),session.getAttribute("password"));
                    AddedResultTest resTest = new AddedResultTest();
                    resaddTest = resTest.addResult(results,idQuestions,idTest,idUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                    if (resaddTest!=null){
                        request.setAttribute("procent",resaddTest);
                }

                request.setAttribute("end","end");
                request.getRequestDispatcher("/form_test/TestDemonstration.jsp").forward(request, response);
            }
        }
                else {
                    System.out.println("Неизвестно количество вопросов или неизвестны типы вопросов");
                }
    }
}
