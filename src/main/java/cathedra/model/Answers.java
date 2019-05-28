package cathedra.model;

import java.util.List;

public class Answers {
    private List<String> answer;

    public Answers(List<String> answer) {
        this.answer = answer;
    }

    public Answers() {
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
