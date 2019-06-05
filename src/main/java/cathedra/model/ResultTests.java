package cathedra.model;

public class ResultTests {
    public String titleTest;
    public String NumCorr;
    public String NumIncorr;

    public ResultTests(String titleTest, String numCorr, String numIncorr) {
        this.titleTest = titleTest;
        this.NumCorr = numCorr;
        this.NumIncorr = numIncorr;
    }

    public ResultTests() {
    }

    public String getTitleTest() {
        return titleTest;
    }

    public String getNumCorr() {
        return NumCorr;
    }

    public String getNumIncorr() {
        return NumIncorr;
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
