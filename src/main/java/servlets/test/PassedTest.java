package servlets.test;


import java.util.Arrays;

public class PassedTest {
    private Integer typeQuest;
    private String[] AnswerSecondType;
    private String Answer;

    public PassedTest() {
    }

    public PassedTest(Integer typeQuest, String[] answerSecondType) {
        this.typeQuest = typeQuest;
        AnswerSecondType = answerSecondType;
    }

    public PassedTest(Integer typeQuest, String answer) {
        this.typeQuest = typeQuest;
        Answer = answer;
    }

    public Integer getTypeQuest() {
        return typeQuest;
    }

    public void setTypeQuest(Integer typeQuest) {
        this.typeQuest = typeQuest;
    }



    public Integer getElementAnswerSecondType(Integer i) {
        return Integer.valueOf(AnswerSecondType[i]);
    }


    public String[] getAnswerSecondType() {
        return AnswerSecondType;
    }

    public void setAnswerSecondType(String[] answerSecondType) {
        AnswerSecondType = answerSecondType;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    @Override
    public String toString() {
        return "PassedTest{" +
                "typeQuest=" + typeQuest +
                ", AnswerSecondType=" + Arrays.toString(AnswerSecondType) +
                ", Answer='" + Answer + '\'' +
                '}';
    }
}
