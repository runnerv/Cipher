import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Cezar {

    private String alphabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,”:-—–!?0123456789 ";

    public String enCrypt(File file, int key) throws IOException {

        InputStream reader = Files.newInputStream(Path.of(String.valueOf(file)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));

        Stream<String> linesCrypto = bufferedReader.lines();

        List<String> strings = linesCrypto.toList();
        StringBuilder sb = new StringBuilder();

        for (String string : strings) {
            if (string.length() == 0) {                 // Проверяем не пустая ли строка в оригинале
                sb.append("\r\n");                      // ... и делаем как было
            }
            for (int i = 0; i < string.length(); i++) {                                    // Шифруем

                int charIndex = alphabet.indexOf(string.charAt(i));       // Получаем индекс символа из нашего алфавита

                int newCharIndex = (charIndex + key) % alphabet.length(); //  Меняем в зависимости от ключа символ
                sb.append(alphabet.charAt(newCharIndex));
                if (i == string.length() - 1) {                    // Если строка закончилась, переходим на новую
                    sb.append("\n");
                }
            }
        }
        return sb.toString();                         // Результат
    }

    public String deCrypt(File file, int key) throws IOException {

        Cezar cezar = new Cezar();
        return cezar.enCrypt(file, (alphabet.length()) - (key % alphabet.length()));  // Дешифруем
    }
}