import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Minjun Jang
 */

public class FileEncryption {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get(args[0]);
        String password;
        Scanner sc = new Scanner(System.in);

        System.out.print("설정할 복구키를 입력하십시오(영문자/숫자/특수문자) > ");
        password = sc.nextLine();
        AES.setKey(password);

        sc.close();

        try(BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            String text;

            sb.append(br.readLine());
            while ((text = br.readLine()) != null) {
                sb.append("\n").append(text);
            }
            AES.encrypt(sb.toString());
        }

        Files.writeString(filePath, AES.getEncryptedText(), StandardCharsets.UTF_8);
    }

}
