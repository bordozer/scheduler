package scheduler.rest.common;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

public class DataGenerator {

    public static UserData generateUserData() {
        String login = generate();
        String username = generate();
        String password = generate();
//        String password = crypt(generate());

        return new UserData(login, username, password);
    }

    private static String generate() {
        return String.valueOf(new Date().getTime());
//        return UUID.randomUUID().toString().substring(0, 8);
    }

    private static String crypt(final String salt) {
        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory f = null;

        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return salt;
        }

        try {
            byte[] hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(salt.getBytes());
    }
}
