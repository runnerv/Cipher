import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Cezar {

    private String alphabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,”:-—!?0123456789 ";

    public String enCrypt(File file, int key) throws IOException {

        InputStream reader = Files.newInputStream(Path.of(String.valueOf(file)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));

        Stream<String> linesCrypto = bufferedReader.lines();

        List<String> strings = linesCrypto.toList();
        StringBuilder sb = new StringBuilder();

        for (String string : strings) {

            for (int i = 0; i < string.length(); i++) {

                int charIndex = alphabet.indexOf(string.charAt(i));
                if(charIndex==-1){sb.append(string.charAt(i));}                                                               // Шифруем
                int newCharIndex = (charIndex + key) % alphabet.length();
                sb.append(alphabet.charAt(newCharIndex));
                if(i==string.length()-1){sb.append("\n");}
            }
        }
        return sb.toString();
    }

    public String deCrypt(File file, int key) throws IOException {

        Cezar cezar = new Cezar();
        return cezar.enCrypt(file, (alphabet.length()) - (key % alphabet.length()));  // Дешифруем
    }
}