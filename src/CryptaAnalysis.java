import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class CryptaAnalysis {

    private ArrayList<Character> listOfCharsCrypto;
    private ArrayList<Character> listOfCharsStat;
    private StringBuilder result = new StringBuilder();
    private List<String> stringsCrypto;
    private List<String> stringsStat;


    public String cryptoAnalysis(File cryptoSource, File statFile) throws IOException {

        InputStream reader = Files.newInputStream(Path.of(String.valueOf(cryptoSource)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));

        Stream<String> linesCrypto = bufferedReader.lines();
        stringsCrypto = linesCrypto.toList();

        reader = Files.newInputStream(Path.of(String.valueOf(statFile)));
        bufferedReader = new BufferedReader(new InputStreamReader(reader));

        Stream<String> linesStat = bufferedReader.lines();
        stringsStat = linesStat.toList();

        HashMap<Character, Integer> mapCrypto = new HashMap();
        HashMap<Character, Integer> mapStat = new HashMap();

        char[] chars = Cezar.alphabet.toCharArray();

        for (char aChar : chars) {                        //   Заполняем HashMap-ы нашим алфавитом

            mapCrypto.put(aChar, 0);
            mapStat.put(aChar, 0);
        }

        fillStatChars(stringsCrypto, mapCrypto);           // Заполняем мапы нашей статистикой из 2 двух источников
        fillStatChars(stringsStat, mapStat);

        listOfCharsCrypto = new ArrayList<>(sortedMapOfChars(mapCrypto).keySet());      // Сортируем мап
        listOfCharsStat = new ArrayList<>(sortedMapOfChars(mapStat).keySet());


        for (String string : stringsCrypto) {
            if (string.length() == 0) {                 // Проверяем не пустая ли строка в оригинале
                result.append(System.lineSeparator());                      // ... и делаем как было
            }
            for (int i = 0; i < string.length(); i++) {
                for (int k = 0; k < listOfCharsCrypto.size(); k++) {      // Меняем символы из одной на символы другой
                    if (string.charAt(i) == listOfCharsCrypto.get(k)) {
                        result.append(listOfCharsStat.get(k));
                        if (i == string.length() - 1) {                    // Если строка закончилась, переходим на новую
                            result.append(System.lineSeparator());
                        }
                    }
                }
            }
        }

        return result.toString();
    }

    private static void fillStatChars(List<String> strings, HashMap<Character, Integer> map) {
        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                for (Map.Entry<Character, Integer> entry : map.entrySet())      // Метод сбора статистики на основе
                                                                                // нашего аллфавита
                    if (entry.getKey() == string.charAt(i)) {
                        int countChars = entry.getValue();
                        map.put(string.charAt(i), (countChars + 1));
                    }
            }
        }
    }


    private static Map sortedMapOfChars(HashMap<Character, Integer> map) {
        Map<Character, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> b - a))
                .collect(LinkedHashMap::new,                                  // Метод сортировки HashMap
                        (m, c) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        return sortedMap;
    }

}