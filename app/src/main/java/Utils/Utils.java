package Utils;

import java.nio.charset.Charset;
import java.util.Random;

public  class Utils {


    public static String getRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}
