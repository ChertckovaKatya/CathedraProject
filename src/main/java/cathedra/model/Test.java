package cathedra.model;

import java.util.List;

public class Test {
     private String question;
     private List<Answers> answers;


    public Test(String question, List<Answers> answers) {
        this.question = question;
        this.answers = answers;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Test{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
