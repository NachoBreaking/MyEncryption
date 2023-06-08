import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Minjun Jang
 */

public class AES {
    private final static String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static String key;
    private static SecretKeySpec secKeySpec;
    private static String encryptedText;
    private static String decryptedText;

    public static void setKey(String password) throws Exception {
        byte[] bytes = password.getBytes("UTF-8");
        byte[] newBytes = new byte[16];

        if(bytes.length < 16) {
            System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
            bytes = newBytes;
        }
        else if(bytes.length > 16){
            System.arraycopy(bytes, 0, newBytes, 0, newBytes.length);
            bytes = newBytes;
        }

        key = new String(bytes, "UTF-8");
        secKeySpec = new SecretKeySpec(key.getBytes(), "AES");
    }

    public static String getEncryptedText() {
        return encryptedText;
    }

    public static String getDecryptedText() {
        return decryptedText;
    }

    public static void encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));
        encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void decrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        cipher.init(Cipher.DECRYPT_MODE, secKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(text));
        decryptedText = new String(decryptedBytes, "UTF-8");
    }

}
