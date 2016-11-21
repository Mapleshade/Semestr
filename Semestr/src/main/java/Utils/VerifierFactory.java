package Utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class VerifierFactory {

    private static Properties properties;


    static {

        properties = new Properties();

        try {

            properties.load(

                    new FileInputStream("C:\\Users\\Elina\\Documents\\Semestr\\resources\\DAO.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static Verifier getVerifier() {
        Verifier verifier = null;

        try {

            verifier = (Verifier) Class.forName(properties.getProperty("verifier.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return verifier;
    }

}
