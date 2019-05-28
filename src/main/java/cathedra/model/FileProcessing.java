package cathedra.model;

import cathedra.contr.DatabaseHandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class FileProcessing {
    DatabaseHandler db = new DatabaseHandler();
    Integer size;

    public Boolean  parsingFile (String name) throws IOException, SQLException {

        File file = new File("D://ProjectCathedra//src//main//java//allFilesTXT",name);
        File catalog = new File("D://ProjectCathedra//src//main//java//allFilesTXT");
        if (file.isFile()) {
            List<String> lines;
            Path filePars = Paths.get("D://ProjectCathedra//src//main//java//allFilesTXT",name);
            if (filePars!=null) {
                lines = Files.readAllLines(filePars, StandardCharsets.UTF_8);
                String first = lines.get(0);
                String[] parametersTest = first.split("\\|");
                String Subject = parametersTest[0];                                 //Название предмета
                Integer idSubject = db.defineTheidSubject(Subject);                   //определяет номер предмета
                Integer NumberOfQuestions = Integer.valueOf(parametersTest[1]);      // Количество вопросов
                Integer LeadTime = Integer.valueOf(parametersTest[2]);               // Время выполнения теста
                Integer TotalScore = Integer.valueOf(parametersTest[3]);             //Общий балл за тест
                if (idSubject!=null) {
                    Boolean TestAdded = db.addTest(idSubject, NumberOfQuestions, LeadTime, TotalScore); // добавляем данные теста
                    Integer idTest = db.defineTheidTest(idSubject, NumberOfQuestions, LeadTime, TotalScore);  // выясняем id теста, который только что добавили
                    if (TestAdded && idTest!=null) {
                        if (NumberOfQuestions>(lines.size()-1)){
                            size = lines.size()-1;
                        }
                        else{
                            size = NumberOfQuestions;
                        }
                        for (int i = 0; i < size; i++) {
                            String next = lines.get(i + 1);
                            String[] QuestionsAnswer = next.split("\\|");
                            Integer typeQuestions = Integer.valueOf(QuestionsAnswer[0]); // тип вопроса
                            String Questions = QuestionsAnswer[1]; // вопрос
                            Integer NumberAnswer = Integer.valueOf(QuestionsAnswer[2]);
                            Boolean QuestionsAdded = db.addQuestions(idTest, Questions, typeQuestions); // добавляем тип вопроса и его формулировку

                            if (QuestionsAdded) {

                                Integer idQuestions = db.defineTheidQuestions(idTest, Questions, typeQuestions); //выясняем id вопроса, который только что добавили
                                if (idQuestions !=null) {
                                    for (int j = 3; j < (NumberAnswer * 2) + 2; j = j + 2) {
                                        String Answer = QuestionsAnswer[j];  //вариант ответа
                                        Integer Mark = Integer.valueOf(QuestionsAnswer[j + 1]); // количество баллов за вариант ответа

                                        Boolean AnswerAdded = db.addAnswer(idQuestions,Answer,Mark);

                                        if (AnswerAdded){
                                            System.out.println("Варианты ответов успешно добавлены");
                                        }
                                    }

                                }
                            }
                        }
                        if (catalog.exists()){
                            Files.delete(filePars);
                        }
                        return true;
                    }

                }
                else {
                    System.out.println("Укажите корректное название предмета");
                    if (catalog.exists()){
                        Files.delete(filePars);
                    }
                    return false;
                }

            }
        }
        else{
            if (catalog.exists()){
            Path filePars = Paths.get("D://ProjectCathedra//src//main//java//allFilesTXT",name);
            Files.delete(filePars);
        }
            System.out.println("не прошел проверку");
            return false;
        }
        if (catalog.exists()){
            Path filePars = Paths.get("D://ProjectCathedra//src//main//java//allFilesTXT",name);
            Files.delete(filePars);
        }
        return false;
    }

    }

