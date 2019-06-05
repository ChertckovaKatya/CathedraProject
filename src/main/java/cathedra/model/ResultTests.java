package cathedra.model;

public class ResultTests {
    private String titleTest;
    private Integer NumCorr;
    private Integer NumIncorr;

    public ResultTests(String titleTest, Integer NumCorr, Integer NumIncorr) {
        this.titleTest = titleTest;
        NumCorr = NumCorr;
        NumIncorr = NumIncorr;
    }

    public ResultTests() {
    }

    public String getTitleTest() {
        return titleTest;
    }

    public void setTitleTest(String titleTest) {
        this.titleTest = titleTest;
    }

    public Integer getNumCorr() {
        return NumCorr;
    }

    public void setNumCorr(Integer numCorr) {
        NumCorr = numCorr;
    }

    public Integer getNumIncorr() {
        return NumIncorr;
    }

    public void setNumIncorr(Integer numIncorr) {
        NumIncorr = numIncorr;
    }

    @Override
    public String toString() {
        return "ResultTests{" +
                "titleTest='" + titleTest + '\'' +
                ", NumCorr=" + NumCorr +
                ", NumIncorr=" + NumIncorr +
                '}';
    }
}
