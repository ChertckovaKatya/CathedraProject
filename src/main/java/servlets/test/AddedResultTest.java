package servlets.test;

import cathedra.contr.DatabaseHandler;

import java.sql.SQLException;
import java.util.List;

public class AddedResultTest {

    public Boolean addResult(List<PassedTest> list,List<Integer> idQuest, Integer idTest, Integer idUser) throws SQLException {
        DatabaseHandler db = new DatabaseHandler();
        Boolean passTest = db.setPassedTest(idTest,idUser,0,0,0,0);
        Integer idPassed = db.getIdPassesTest(idTest,idUser,0,0,0,0);
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getTypeQuest()==1 ||list.get(i).getTypeQuest()==3 ) {
                Boolean firstRes = db.setSelectedAnswer(idPassed, idQuest.get(i), list.get(i).getElementAnswerSecondType(0), null);
                    if (firstRes){
                        System.out.println("элемент первого типа успешно добавлен");
                    }
                    else{
                        System.out.println("элемент первого не добавлен");
                    }
            }
            if (list.get(i).getTypeQuest()==2){
                for (int j=0; j<list.get(i).getAnswerSecondType().length; j++) {
                    Boolean secondRes = db.setSelectedAnswer(idPassed, idQuest.get(i), list.get(i).getElementAnswerSecondType(j), null);
                    if (secondRes){
                        System.out.println("элемент второго типа успешно добавлен");
                    }
                    else{
                        System.out.println("элемент второго не добавлен");
                    }
                }
            }
            if (list.get(i).getTypeQuest()==3){
                Boolean thirdRes = db.setSelectedAnswer(idPassed, idQuest.get(i),null, list.get(i).getAnswer());
                if (thirdRes){
                    System.out.println("элемент третьего типа успешно добавлен");
                }
                else{
                    System.out.println("элемент третьего не добавлен");
                }
            }
        }

        return true;
    }
}
