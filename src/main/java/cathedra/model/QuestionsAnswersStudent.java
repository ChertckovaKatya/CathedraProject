package cathedra.model;



public class QuestionsAnswersStudent {
    private String answCorrect;
    private String answStudent;

    public QuestionsAnswersStudent() {
    }

    public QuestionsAnswersStudent(String answCorrect, String answStudent) {
        this.answCorrect = answCorrect;
        this.answStudent = answStudent;
    }

    public String getAnswCorrect() {
        return answCorrect;
    }

    public void setAnswCorrect(String answCorrect) {
        this.answCorrect = answCorrect;
    }

    public String getAnswStudent() {
        return answStudent;
    }

    public void setAnswStudent(String answStudent) {
        this.answStudent = answStudent;
    }

    @Override
    public String toString() {
        return "QuestionsAnswersStudent{" +
                "answCorrect='" + answCorrect + '\'' +
                ", answStudent='" + answStudent + '\'' +
                '}';
    }
}
