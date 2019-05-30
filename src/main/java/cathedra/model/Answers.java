package cathedra.model;

import java.util.List;

public class Answers {
    private Integer idAnswer;
    private String value;

    public Answers(Integer idAnswer, String value) {
        this.idAnswer = idAnswer;
        this.value = value;
    }

    public Answers(List answer) {
    }

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
