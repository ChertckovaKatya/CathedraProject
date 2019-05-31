package servlets.test;

import cathedra.contr.DatabaseHandler;
import cathedra.model.QuestionsAnswersStudent;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class AddedResultTest {

    public Integer addResult(List<PassedTest> list,List<Integer> idQuest, Integer idTest, Integer idUser) throws SQLException {
        System.out.println(list.toString());
        DatabaseHandler db = new DatabaseHandler();
        Boolean passTest = db.setPassedTest(idTest,idUser,0,0,0,0);
        Integer idPassed = null;
        if (passTest) {
             idPassed = db.getIdPassesTest(idTest, idUser, 0, 0, 0, 0);
        }
        if (idPassed!=null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTypeQuest() == 1) {
                    Boolean firstRes = db.setSelectedAnswer(idPassed, idQuest.get(i), list.get(i).getElementAnswerSecondType(0), null);
                    if (firstRes) {
                        System.out.println("элемент первого типа успешно добавлен");
                    } else {
                        System.out.println("элемент первого не добавлен");
                    }
                }
                if (list.get(i).getTypeQuest() == 2) {
                    for (int j = 0; j < list.get(i).getAnswerSecondType().length; j++) {
                        Boolean secondRes = db.setSelectedAnswer(idPassed, idQuest.get(i), list.get(i).getElementAnswerSecondType(j), null);
                        if (secondRes) {
                            System.out.println("элемент второго типа успешно добавлен");
                        } else {
                            System.out.println("элемент второго не добавлен");
                        }
                    }
                }
                if (list.get(i).getTypeQuest() == 3) {
                    Boolean thirdRes = db.setSelectedAnswer(idPassed, idQuest.get(i), 0, list.get(i).getAnswer());
                    if (thirdRes) {
                        System.out.println("элемент третьего типа успешно добавлен");
                    } else {
                        System.out.println("элемент третьего не добавлен");
                    }
                }
            }
        }
        Integer result = getNumCorrectAnswer(idPassed,idTest);
        return result;
    }

    public Integer getNumCorrectAnswer (Integer idPassedTest, Integer idTest) throws SQLException {
        DatabaseHandler db = new DatabaseHandler();
        Integer numCorrectMarkFirstType = db.getNumCorrectFirstTypeAnswers(idPassedTest);
        List<QuestionsAnswersStudent> answerCorrectAndStudentSecondType = db.getCorrectSecondTypeAnswers(idPassedTest);
        List<QuestionsAnswersStudent> answerCorrectAndStudentThirdType = db.getCorrectThirdTypeAnswers(idPassedTest);
        int[] allPoints = db.getOfNumPointsCorrectAnswer(idTest);
        int pointsOneCorrectAnswer = allPoints[1];
        int totalScore = allPoints[0];
        int pointsSecond = 0;
        for (int i=0; i<answerCorrectAndStudentSecondType.size(); i++){

           int[] mass1 =  Arrays.asList(answerCorrectAndStudentSecondType.get(i).getAnswCorrect().split(",")).stream().mapToInt(Integer::parseInt).toArray();
           int[] mass2 =  Arrays.asList(answerCorrectAndStudentSecondType.get(i).getAnswStudent().split(",")).stream().mapToInt(Integer::parseInt).toArray();
            int p = 0;
           if(mass2!=null) {

                for (int j = 0; j < mass1.length; j++) {
                    int num = mass1[j];
                    boolean res = IntStream.of(mass2).anyMatch(x -> x == num);
                    if (res) {
                        p++;
                    }
                }
                if (p != 0) {
                    if (mass2.length == p) {
                        pointsSecond = pointsSecond + pointsOneCorrectAnswer;
                    } else {
                        if ((mass2.length / p) < 2 || (mass2.length / p) == 2 ){
                            if ((mass2.length % p) != 0) {
                            pointsSecond = pointsSecond + Math.round(pointsOneCorrectAnswer / 2);
                            }
                        }
                    }
                }
            }

        }
        System.out.println("pointsSecond="+pointsSecond);
        int pointsThird = 0;
        for (int i=0; i< answerCorrectAndStudentThirdType.size(); i++){
            String answerCorrect = answerCorrectAndStudentThirdType.get(i).getAnswCorrect();
            String answerStudent = answerCorrectAndStudentThirdType.get(i).getAnswStudent();
            if (answerCorrect.equals(answerStudent)){
                pointsThird = pointsThird + pointsOneCorrectAnswer;
            }
        }
        System.out.println(pointsThird);

        int SumPoints = numCorrectMarkFirstType + pointsSecond + pointsThird;
        int numCorrectAnswers = (int)Math.round(Double.valueOf(SumPoints)/Double.valueOf(pointsOneCorrectAnswer));
        int NumIncorreсtAnswer = (totalScore/pointsOneCorrectAnswer) - numCorrectAnswers;
        boolean updatePassedTest = db.updateDatePassedTest(0,numCorrectAnswers,NumIncorreсtAnswer, SumPoints,idPassedTest );
        if (updatePassedTest){
            System.out.println("Данные обновлены");
        }
        int proc = Math.round(SumPoints * 100 / totalScore);
        System.out.println(numCorrectMarkFirstType);
        System.out.println(answerCorrectAndStudentSecondType.toString());
        System.out.println(answerCorrectAndStudentThirdType.toString());
        return proc;
    }
}
