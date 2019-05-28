package cathedra.model;

public class AllTests {
    public Integer idTest;
    public String titleTest;

    public AllTests() {
    }

    public AllTests(Integer idTest, String titleTest) {
        this.idTest = idTest;
        this.titleTest = titleTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public String getTitleTest() {
        return titleTest;
    }

    @Override
    public String toString() {
        return "AllTests{" +
                "idTest=" + idTest +
                ", titleTest='" + titleTest + '\'' +
                '}';
    }
}
