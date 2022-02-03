import java.io.*;
import java.util.Scanner;

public class BruteForce {

    private String result = null;
    private Cezar cezar = new Cezar();
    private StringBuilder sb = null;

    public String bruteForce(File file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < Cezar.alphabet.length(); i++) {

            int keyDecryptIs = 0;
            int countValidity = 0;
            sb = new StringBuilder(cezar.deCrypt(file, i));
            char[] chars = sb.toString().toCharArray();
            for (int k = 0; k < chars.length - 1; k++) {             // Перебираем варианты

                if (Character.isWhitespace(chars[k]) ||
                        chars[k]=='.' && chars[k + 1]==' ' ||
                        chars[k]==',' && chars[k + 1]==' ')
                countValidity++;                      // Копим валидность
                keyDecryptIs = i;
            }

            if (countValidity > sb.toString().length() / 10) {
                System.out.println(System.lineSeparator());
                System.out.println(sb.substring(0, 100));
                System.out.println(System.lineSeparator());
                System.out.println("Текст читабелен?" + System.lineSeparator());
                System.out.println("1. ДА (Сохраняем)    или    2. НЕТ (Подбираем дальше)");
                String status = scanner.nextLine();
                if (status.equals("1")) {
                    sb.append(result);
                    System.out.printf("Вам повезло, шифр взломан c %d попытки и успешно сохранен.\n\n", keyDecryptIs);
                    break;
                } else if (status.equals("2")) {
                    continue;
                }
            } else
                System.out.printf("Попытка № %d,ломаем дальше\n",keyDecryptIs);
        }
        return sb.toString();
    }
}