package dateClasses;

import java.nio.charset.Charset;
import java.util.Random;

public class TestHelper {
    public static String generateRandomString () {
        byte[] array = new byte[7]; // длина ограничена 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}
