import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Minjun Jang
 */

public class FileDecryption {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get(args[0]);
        String password;
        Scanner sc = new Scanner(System.in);

        System.out.print("복구키를 입력하십시오 > ");
        password = sc.nextLine();
        AES.setKey(password);

        sc.close();

        try(BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            AES.decrypt(br.readLine());
        }

        Files.writeString(filePath, AES.getDecryptedText(), StandardCharsets.UTF_8);
    }

}
