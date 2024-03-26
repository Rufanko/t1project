package pages;

public class TestHelperUI {

    public static int generateToNumbers (int lower, int upper) {
        int r = (int) (Math.random() * (upper - lower)) + lower;
        return r;
    }
}
