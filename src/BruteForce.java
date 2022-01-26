import java.io.*;
import java.util.Scanner;

public class BruteForce {

    private String result = null;
    private Cezar cezar = new Cezar();
    private StringBuilder sb = null;
    private String tempResult;

    public String bruteForce(File file) throws IOException {

        for (int i = 0; i < Cezar.alphabet.length(); i++) {


            int keyDecryptIs = 0;
            int countValidity = 0;
            sb = new StringBuilder(cezar.deCrypt(file, i));
            char[] chars = sb.toString().toCharArray();
            for (int k = 0; k < chars.length - 1; k++) {             // Перебираем варианты
                boolean charsPartEqual = String.valueOf(chars[k + 1]).equals(" ");
                if (Character.isWhitespace(chars[k]) ||
                        String.valueOf(chars[k]).equals(".") && charsPartEqual ||
                        String.valueOf(chars[k]).equals(".") && charsPartEqual)
                    countValidity++;                      // Копим валидность
                keyDecryptIs = i;
            }
            Scanner scanner = new Scanner(System.in);
            if (countValidity > sb.toString().length() / 10) {
                System.out.println(System.lineSeparator());
                System.out.println(sb.substring(0, 10));
                System.out.println(System.lineSeparator());
                System.out.println("Текст читабелен?"+System.lineSeparator());
                System.out.println("1. Если - ДА    или   2. Если - НЕТ");
                String status = scanner.nextLine();
                if (status.equals("1")) {
                    sb.append(result);
                    System.out.printf("Вам повезло, шифр взломан c %d попытки и успешно сохранен.\n\n", keyDecryptIs);
                    break;
                } else if (status.equals("2")) {

                    sb = new StringBuilder(cezar.deCrypt(file, i));
                    for (int k = keyDecryptIs; k < chars.length - 1; k++) {             // Перебираем варианты
                        boolean charsPartEqual = String.valueOf(chars[k + 1]).equals(" ");
                        if (Character.isWhitespace(chars[k]) ||
                                String.valueOf(chars[k]).equals(".") && charsPartEqual ||
                                String.valueOf(chars[k]).equals(".") && charsPartEqual)
                            countValidity++;                      // Копим валидность
                        keyDecryptIs = i;
                        sb.append(result);
                    }

                }

            } else
                System.out.println("Попытка № " + keyDecryptIs + ", " + "ломаем дальше");
        }
        return sb.toString();

    }

}