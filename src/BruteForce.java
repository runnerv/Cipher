import java.io.*;

public class BruteForce {

    private String result = null;
    private Cezar cezar = new Cezar();
    private StringBuilder sb = null;

    public String bruteForce(File file) throws IOException {

        for (int i = 0; i < 134; i++) {
            int keyDecryptIs = 0;
            int countValidity = 0;
            sb = new StringBuilder(cezar.deCrypt(file, i));
            char[] chars = sb.toString().toCharArray();                        // Перебираем варианты
            for (int k = 0; k < chars.length-1; k++) {
                if (Character.isWhitespace(chars[k]) ||
                        String.valueOf(chars[k]).equals(".") && String.valueOf(chars[k+1]).equals(" "))
                    countValidity++;
                keyDecryptIs = i;
            }

            if (countValidity > sb.toString().length() / 10) {

                sb.append(result);
                System.out.printf("Вам повезло, шифр взломан c %d попытки и успешно сохранен.\n\n", keyDecryptIs);
                break;
            } else
                System.out.println("Попытка № " + keyDecryptIs + ", " + "ломаем дальше");
        }
        return sb.toString();

    }

}