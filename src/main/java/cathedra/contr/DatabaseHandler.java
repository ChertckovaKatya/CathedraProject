package cathedra.contr;

import cathedra.model.AllTests;
import cathedra.model.Answers;
import cathedra.model.Test;
import cathedra.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cathedra.contr.Config.*;


public class DatabaseHandler {
    private Connection conn = null;
    private ResultSet result;
    private PreparedStatement preStm;
    public static Statement dbConnection;
    private static List<String> answer_out = new ArrayList<>();

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

    public Boolean addTest(Integer idSubjectTest, Integer QuantityQuestions,Integer LeadTime, Integer TotalScore){
        String query= "INSERT INTO test (idSubjectTest, QuantityQuestions, LeadTime, TotalScore)"+" VALUES (?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idSubjectTest);
            preStm.setInt(2,QuantityQuestions);
            preStm.setInt(3,LeadTime);
            preStm.setInt(4,TotalScore);
            preStm.execute();
//            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer defineTheidSubject (String subject) throws SQLException {
        Integer idSubject = null;
        String query= "SELECT idSubject FROM subject WHERE SubjectName=\""+subject+"\"";
//        System.out.println(query);
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

    public Integer defineTheidTest (Integer idSubjectTest, Integer QuantityQuestions,Integer LeadTime, Integer TotalScore) throws SQLException {
        Integer idTest = null;
        String query = "SELECT idTest FROM test where idSubjectTest="+idSubjectTest+" AND QuantityQuestions="+QuantityQuestions+" AND LeadTime="+LeadTime+" AND TotalScore="+TotalScore+" ORDER\n" +
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

    public Boolean addQuestions (Integer idTestQuestions,String QuestionWording, Integer TypeQues){

        String query= "INSERT INTO  questions (idTestQuestions, QuestionWording, TypeQues)"+" VALUES (?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idTestQuestions);
            preStm.setString(2,QuestionWording);
            preStm.setInt(3,TypeQues);
            preStm.execute();
//            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer defineTheidQuestions(Integer idTestQuestions,String QuestionWording, Integer TypeQues) throws SQLException {
        Integer idQuestions = null;
        String query = "SELECT idQuestions from questions where QuestionWording=\""+QuestionWording+"\" AND TypeQues="+TypeQues+" AND questions.idTestQuestions="+idTestQuestions+";";
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
        Integer id = null;
        String query = "SELECT idPassed from passedtests where idTest=\""+idTest+"\" AND idStudents=\""+idStudents+"\" AND LeadTime=\""+LeadTime+"\" AND NumCorreсtAnswer=\""+NumCorreсtAnswer+"\" AND NumIncorreсtAnswer=\""+NumIncorreсtAnswer+"\" AND Point=\""+Point+"\";";
        ResultSet quest = resultQuery(query);
        if (quest.next()){
            return (quest.getInt("idPassed"));
        }
        return id;
    }

    public Integer getIdStudents (Object login, Object password) throws SQLException {
        Integer idStudents = null;
        String query_quest = "SELECT id from User where Name=\""+login+"\" AND Password=\""+password+"\";";
        ResultSet quest = resultQuery(query_quest);
        if (quest.next()){
            return (quest.getInt("id"));
        }
        return idStudents;
    }

    public Boolean setSelectedAnswer(Integer idPassedTest,Integer idQuestionPassedTest,Integer idAnswerPassedTest,String SelfEnteredAnswer){
        String query= "INSERT INTO  selectedanswer (idPassedTest, idQuestionPassedTest, idAnswerPassedTest, `Self-enteredAnswer`)"+" VALUES (?,?,?,?)";
        try {
            preStm = conn.prepareStatement(query);
            preStm.setInt(1,idPassedTest);
            preStm.setInt(2,idQuestionPassedTest);
            preStm.setInt(3,idAnswerPassedTest);
            preStm.setString(4,SelfEnteredAnswer);
            preStm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

}