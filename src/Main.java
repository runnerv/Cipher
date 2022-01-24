import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    static String startMenu = "Криптопрограмма pre-alpha build 0.1\n\n" +
            "Выберите действие:\n\n" +
            "1. Зашифровать тест.\n" +
            "2. Расшифровать тест.\n" +
            "3. Взломать текст с помощью алгоритма BruteForce.\n" +
            "4. Расшифровать текст с помощью статистического анализа.\n\n" +
            "Или наберите \"exit\" для выхода.\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(startMenu);
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("Укажите путь к файлу для шифрования.");
            String source = scanner.nextLine();
            if (!Files.isRegularFile(Path.of(source))) {
                System.out.println("Неверный файл");
                return;
            }
            System.out.println("Укажите путь к файлу для сохранения результата.");
            String src = scanner.nextLine();
            System.out.println("Укажите ключ для шифрования.");
            int key = scanner.nextInt();
            try {
                write(src, new Cezar().enCrypt(new File(source), key));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Информация зашифрована...");


        } else if (choice.equals("2")) {
            System.out.println("Укажите путь к файлу для расшифровки.");
            String source = scanner.nextLine();
            if (!Files.isRegularFile(Path.of(source))) {
                System.out.println("Неверный файл");
                return;
            }
            System.out.println("Укажите путь файлу для сохранения результата.");
            String src = scanner.nextLine();
            System.out.println("Укажите ключ для расшифровки.");
            int key = scanner.nextInt();
            try {
                write(src, new Cezar().deCrypt(new File(source), key));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Информация расшифрована...");


        } else if (choice.equals("3")) {
            BruteForce bruteForce = new BruteForce();
            System.out.println("Укажите путь к файлу для взлома");
            String source = scanner.nextLine();
            if (!Files.isRegularFile(Path.of(source))) {
                System.out.println("Неверный файл");
                return;
            }
            System.out.println("Укажите путь к файлу для сохранения результата");
            String src = scanner.nextLine();
            try {
                write(src, new BruteForce().bruteForce(new File(source)));
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (choice.equals("4")) {
            System.out.println("Укажите путь к зашифрованному файлу... ");
            String source = scanner.nextLine();
            if (!Files.isRegularFile(Path.of(source))) {
                System.out.println("Неверный файл");
                return;
            }
            System.out.println("Укажите путь к файлу лингвистической статистики... ");
            String sourceStat = scanner.nextLine();
            if (!Files.isRegularFile(Path.of(source))) {
                System.out.println("Неверный файл");
                return;
            }
            System.out.println("Укажите путь к файлу для сохранения результата");
            String src = scanner.nextLine();
            try {
                write(src, new CryptaAnalysis().cryptoAnalysis(new File(source), new File(sourceStat)));
            } catch (IOException e) {
                e.printStackTrace();
            }System.out.println("Как смог, так и расшифровал...");


        } else if (choice.equals("exit")) {
            System.out.println("\nПрограмма закрывается... \n");
        }

    }


    public static void write(String fileName, String text) {

        File file = new File(fileName);                                                                //Определяем файл

        try {                                                    //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile()); //PrintWriter обеспечит возможности записи в файл
            try {
                out.print(text);                                                               //Записываем текст в файл
            } finally {
                out.close();                                                         //После чего мы должны закрыть файл
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}







