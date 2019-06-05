package cathedra.contr;

import cathedra.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static cathedra.contr.Config.*;


public class DatabaseHandler {
    private Connection conn = null;
    private ResultSet result;
    private PreparedStatement preStm;
    public static Statement dbConnection;
    private static List<String> answer_out = new ArrayList<String>();

    public DatabaseHandler() {
//        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
//        System.out.println(myUrl);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(myUrl, dbUser, dbPass);
//            dbConnection = DriverManager.getConnection(myUrl,dbUser,dbPass).createStatement();
//            result = dbConnection.executeQuery(query);
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка подключения к базе данных!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных!");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.conn;
    }


    public ResultSet resultQuery (String query) {
        try {
            dbConnection = this.conn.createStatement();
            result = dbConnection.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Boolean CheckLoginName (User user) throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        String name = user.getName();
        String password = user.getPassword();
        String query = "SELECT Name,Password from User where Name='"+ name +"' AND Password = '" + password + "';";
        //System.out.println("SELECT Name,Password from User where Name='"+ name +"' AND Password = '" + password + "';");
        result = resultQuery(query);
        if (result.next()) {
            return true;
        }
        else {
            return false;
        }
    }



    public User.ROLE getRoleByLoginPassword(User user) throws SQLException, ClassNotFoundException {
        User.ROLE role = User.ROLE.UNKNOWN;
        ResultSet result = null;
        String line_role = null;
        String name = user.getName();
        String password = user.getPassword();
        String query = "SELECT Role from User where Name='"+ name +"' AND Password = '" + password + "';";
        // System.out.println("SELECT Role from User where Name='"+ name +"' AND Password = '"+ password +"';");
        try {
            result = resultQuery(query);
            while (result.next()) {
                line_role = result.getString("Role");
                System.out.println(result.getString("Role"));
            }


            if (line_role.equals("USER")){
                role= User.ROLE.USER;
            }
            else{
                if(line_role.equals("ADMIN")){
                    role= User.ROLE.ADMIN;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public Boolean addTest(Integer idSubjectTest,String TitleTest, Integer QuantityQuestions,Integer LeadTime, Integer TotalScore, Integer PointsForOneCorrectAnswer){
        String query= "INSERT INTO test (idSubjectTest,TitleTest, QuantityQuestions, LeadTime,TotalScore,PointsForOneCorrectAnswer)"+" VALUES (?,?,?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idSubjectTest);
            preStm.setString(2,TitleTest);
            preStm.setInt(3,QuantityQuestions);
            preStm.setInt(4,LeadTime);
            preStm.setInt(5,TotalScore);
            preStm.setInt(6,PointsForOneCorrectAnswer);
            preStm.execute();
//            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer defineTheidSubject (String subject) throws SQLException {
        System.out.println(subject);
        Integer idSubject = null;
        String query= "SELECT idSubject FROM subject WHERE SubjectName=\""+subject+"\";";
        System.out.println(query);
        ResultSet id = resultQuery(query);
        if (id.next()) {
            idSubject = id.getInt("idSubject");
//            System.out.println(idSubject);
        }
        else {
            System.out.println("Предмета с названием: "+subject+" не существует");
        }
        return idSubject;
    }

    public Integer defineTheidTest (Integer idSubjectTest,String TitleTest, Integer QuantityQuestions,Integer LeadTime, Integer TotalScore,Integer PointsForOneCorrectAnswer) throws SQLException {
        Integer idTest = null;
        String query = "SELECT idTest FROM test where idSubjectTest="+idSubjectTest+"  AND TitleTest=\""+TitleTest+"\" AND QuantityQuestions="+QuantityQuestions+" AND LeadTime="+LeadTime+" AND TotalScore="+TotalScore+" AND PointsForOneCorrectAnswer = "+PointsForOneCorrectAnswer+" ORDER\n" +
                "    BY idTest DESC LIMIT 1;";
        ResultSet id = resultQuery(query);
        if(id.next()){
            idTest = id.getInt("idTest");
        }
        else{
            System.out.println("Теста с данными параметрами не существует");
        }
        return idTest;
    }

    public Boolean addQuestions (Integer idTestQuestions,String QuestionWording, Integer TypeQues, Integer QuantityAnswer){

        String query= "INSERT INTO  questions (idTestQuestions, QuestionWording, TypeQues,QuantityAnswer)"+" VALUES (?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idTestQuestions);
            preStm.setString(2,QuestionWording);
            preStm.setInt(3,TypeQues);
            preStm.setInt(4,QuantityAnswer);
            preStm.execute();
//            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer defineTheidQuestions(Integer idTestQuestions,String QuestionWording, Integer TypeQues, Integer QuantityAnswer) throws SQLException {
        Integer idQuestions = null;
        String query = "SELECT idQuestions from questions where QuestionWording=\""+QuestionWording+"\" AND TypeQues="+TypeQues+" AND questions.idTestQuestions="+idTestQuestions+" AND QuantityAnswer="+QuantityAnswer+";";
        ResultSet id = resultQuery(query);
        if (id.next()){
            idQuestions = id.getInt("idQuestions");
        }
        else {
            System.out.println("Вопроса с данными параметрами не существует");
        }
        return idQuestions;
    }

    public Boolean addAnswer(Integer idQuestionAnswer,String AnswerWording,Integer Mark){

        String query= "INSERT INTO answer (idQuestionAnswer, AnswerWording, Mark)"+" VALUES (?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idQuestionAnswer);
            preStm.setString(2,AnswerWording);
            preStm.setInt(3,Mark);
            preStm.execute();
//            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

    public List getTests () throws SQLException {
        String query = "SELECT idTest,TitleTest from test;";
        ResultSet alltest = resultQuery(query);
        List<AllTests> results= new ArrayList<>();
        while(alltest.next()) {
            results.add(new AllTests(alltest.getInt("idTest"),alltest.getString("TitleTest")));
        }
        return results;
    }

    public Integer getQuentityQuestions(Integer idTest) throws SQLException {
        Integer quantity = null;
        String query = "Select QuantityQuestions from test where idTest=\""+idTest+"\";";
        ResultSet result = resultQuery(query);
        if (result.next()){
            quantity = result.getInt("QuantityQuestions");
        }
        else {
            System.out.println("Воросов не существует");
        }
        return quantity;
    }

    public ArrayList getTypeQuestions(Integer idTest) throws SQLException {
        ArrayList<Integer> list_types = new ArrayList<>();
        String query_type = "SELECT TypeQues FROM questions where idTestQuestions=\""+idTest+"\" ;";
//        System.out.println("SELECT TypeQues FROM questions where idTestQuestions=\""+idTest+"\" ;");
        ResultSet result = resultQuery(query_type);
        while (result.next()){
            Integer number = result.getInt("TypeQues");
//            System.out.println(number);
            list_types.add(number);
            }
        return list_types;
    }

    public List getidQuestions(Integer idTest) throws SQLException {
        String query_idQuest = "select idQuestions from questions where idTestQuestions=\"" + idTest + "\";";
        ResultSet list_idOuest = resultQuery(query_idQuest);
        List<Integer> idQuest = new ArrayList<>();
        while (list_idOuest.next()) {
            idQuest.add(list_idOuest.getInt("idQuestions"));
        }
        return idQuest;
    }
    public List getAnswerWording(Integer idTest, Integer idQuest) throws SQLException {
        List<Answers> answer = new ArrayList<>();
       String query_answer = "SELECT  answer.idAnswer, answer.AnswerWording\n" +
                "FROM answer join questions ON answer.idQuestionAnswer = questions.idQuestions\n" +
                "WHERE idTestQuestions=\""+idTest+"\" AND idQuestionAnswer = \""+idQuest+"\";";
        ResultSet list_answer = resultQuery(query_answer);
        while(list_answer.next()){
            answer.add(new Answers(list_answer.getInt("answer.idAnswer"), list_answer.getString("answer.AnswerWording")));
        }
        return answer;
    }

    public String getQuestionWording(Integer idTest, Integer idQuest) throws SQLException {
        String query_quest = "SELECT QuestionWording from questions where idTestQuestions=\""+idTest+"\" AND idQuestions=\""+idQuest+"\";";
        ResultSet quest = resultQuery(query_quest);
        if (quest.next()){
            return (quest.getString("QuestionWording"));
        }
        return null;
    }


    public List getQuestAnswer(Integer idTest, Integer idOuest) throws SQLException {
        List <Test> list_quest_answer =  new ArrayList<>();
        String quest = getQuestionWording(idTest,idOuest);
        List answer = getAnswerWording(idTest,idOuest);
        list_quest_answer.add(new Test(quest,answer));
        return list_quest_answer;
    }

    public Boolean setPassedTest(Integer idTest,Integer idStudents,Integer LeadTime,Integer NumCorreсtAnswer,Integer NumIncorreсtAnswer,Integer Point){
        String query= "INSERT INTO passedtests (idTest, idStudents, LeadTime, NumCorreсtAnswer, NumIncorreсtAnswer, Point)"+" VALUES (?,?,?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idTest);
            preStm.setInt(2,idStudents);
            preStm.setInt(3,LeadTime);
            preStm.setInt(4,NumCorreсtAnswer);
            preStm.setInt(5,NumIncorreсtAnswer);
            preStm.setInt(6,Point);
            preStm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getIdPassesTest (Integer idTest,Integer idStudents,Integer LeadTime,Integer NumCorreсtAnswer,Integer NumIncorreсtAnswer,Integer Point) throws SQLException {
        String query = "SELECT idPassed from passedtests where idTest="+idTest+" AND idStudents="+idStudents+" AND LeadTime="+LeadTime+" AND NumCorreсtAnswer="+NumCorreсtAnswer+" AND NumIncorreсtAnswer="+NumIncorreсtAnswer+" AND Point="+Point+";";
        ResultSet quest = resultQuery(query);
        if (quest.next()){
            return (quest.getInt("idPassed"));
        }
        return null;
    }

    public Integer getIdStudents (Object login, Object password) throws SQLException {
        String query_quest = "SELECT id from User where Name=\""+login+"\" AND Password=\""+password+"\";";
        ResultSet quest = resultQuery(query_quest);
        if (quest.next()){
            return (quest.getInt("id"));
        }
        return null;
    }

    public Boolean setSelectedAnswer(Integer idPassedTest,Integer idQuestionPassedTest,Integer idAnswerPassedTest,String SelfEnteredAnswer){
        String query= "INSERT INTO  selectedanswer (idPassedTest, idQuestionPassedTest, idAnswerPassedTest, `Self-enteredAnswer`)"+" VALUES (?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idPassedTest);
            preStm.setInt(2,idQuestionPassedTest);
            preStm.setInt(3, idAnswerPassedTest);
            preStm.setString(4,SelfEnteredAnswer);
            preStm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public Integer getNumCorrectFirstTypeAnswers(Integer idPassedTest) throws SQLException {
        String query = "select SUM(Mark) as sum\n" +
                "from answer\n" +
                "where idAnswer IN (select idAnswerPassedTest from selectedanswer where idPassedTest="+idPassedTest+")\n" +
                "AND idQuestionAnswer IN (select idQuestions from questions where TypeQues=1);";
        ResultSet result = resultQuery(query);
        if (result.next()){
            return (result.getInt("sum"));
        }
        return null;

    }

    public List getCorrectSecondTypeAnswers(Integer idPassedTest) throws SQLException{
        List<QuestionsAnswersStudent> answers = new ArrayList<>();
        String query = "select  ansCorrect, ansStudent\n" +
                "from\n" +
                "(SELECT idQuestionAnswer, group_concat(distinct idAnswer) as ansCorrect\n" +
                "  from  answer\n" +
                "    join questions on (answer.idQuestionAnswer = questions.idQuestions and questions.TypeQues=2)\n" +
                "    join selectedanswer on (answer.idQuestionAnswer = selectedanswer.idQuestionPassedTest and selectedanswer.idPassedTest = "+idPassedTest+")\n" +
                "  WHERE\n" +
                "    answer.Mark!=0\n" +
                "  group by idQuestionAnswer)tabl1\n" +
                "join (SELECT idQuestionPassedTest,  group_concat(distinct idAnswerPassedTest) AS ansStudent\n" +
                "from selectedanswer\n" +
                "join questions on (selectedanswer.idQuestionPassedTest = questions.idQuestions and  questions.TypeQues=2 )\n" +
                "WHERE selectedanswer.idPassedTest = "+idPassedTest+"\n" +
                "GROUP BY  idQuestionPassedTest) tabl2 on (tabl1.idQuestionAnswer = tabl2.idQuestionPassedTest);";
        ResultSet result = resultQuery(query);
        while (result.next()){
            answers.add(new QuestionsAnswersStudent(result.getString("ansCorrect"),result.getString("ansStudent")));
        }
        return answers;
    }

    public List getCorrectThirdTypeAnswers(Integer idPassedTest) throws SQLException {
        List<QuestionsAnswersStudent> answers = new ArrayList<>();
        String query = "select  ansCorrect, ansStudent\n" +
                "from\n" +
                "  (select answer.idQuestionAnswer as idOuest, answer.AnswerWording AS ansCorrect\n" +
                "    from answer\n" +
                "      join questions ON answer.idQuestionAnswer = questions.idQuestions\n" +
                "        join selectedanswer on (answer.idQuestionAnswer = selectedanswer.idQuestionPassedTest and selectedanswer.idPassedTest = \""+idPassedTest+"\")\n" +
                "   WHERE questions.TypeQues = 3) AS tabl1\n" +
                "join\n" +
                "  (SELECT idQuestionPassedTest,`Self-enteredAnswer` AS ansStudent\n" +
                "FROM selectedanswer\n" +
                "WHERE idPassedTest="+idPassedTest+" AND idAnswerPassedTest=0) AS tabl2\n" +
                " on (tabl1.idOuest = tabl2.idQuestionPassedTest);";
        ResultSet result = resultQuery(query);
        while (result.next()){
            answers.add(new QuestionsAnswersStudent(result.getString("ansCorrect"),result.getString("ansStudent")));
        }
        return answers;
    }

    public int[] getOfNumPointsCorrectAnswer (Integer idTest) throws SQLException {
        String query = "select TotalScore,PointsForOneCorrectAnswer FROM test WHERE idTest = "+idTest+";";
        ResultSet result = resultQuery(query);
        int[] res = new int[2];
        if (result.next()){
            res[0] = result.getInt("TotalScore");
            res[1] = result.getInt("PointsForOneCorrectAnswer");
            return res ;
        }
        return null;
    }

    public boolean updateDatePassedTest(Integer LeadTime, Integer NumCorreсtAnswer, Integer NumIncorreсtAnswer, Integer Points, Integer idPassed){
        String query = "UPDATE passedtests SET LeadTime = ?, NumCorreсtAnswer = ?, NumIncorreсtAnswer= ?, Point = ? WHERE idPassed = ? ;";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,LeadTime);
            preStm.setInt(2,NumCorreсtAnswer);
            preStm.setInt(3,NumIncorreсtAnswer);
            preStm.setInt(4,Points);
            preStm.setInt(5,idPassed);
            preStm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public List getInfResultsTests (Integer idUser) throws SQLException {
        List<ResultTests> resultsTest = new ArrayList<>();
        String query = "select test.TitleTest , passedtests.NumCorreсtAnswer, passedtests.NumIncorreсtAnswer\n" +
                "from passedtests JOIN test ON passedtests.idTest = test.idTest\n" +
                "WHERE idStudents ="+idUser+";";
        ResultSet result = resultQuery(query);
        while (result.next()){
            resultsTest.add(new ResultTests(result.getString("TitleTest"),result.getString("passedtests.NumCorreсtAnswer"),result.getString("passedtests.NumIncorreсtAnswer")));
        }
        return resultsTest;
    }


}
