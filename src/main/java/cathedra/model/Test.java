package cathedra.model;

import java.util.List;

public class Test {
     private String question;
     private List<String> answers;

    public Test(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Test() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
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
