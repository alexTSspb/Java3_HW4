import java.io.*;
import java.util.Scanner;

public class Ex3 {
    private static RandomAccessFile randomAccessFile = null;
    private static int PAGE_SIZE = 1800;
    public static void main(String[] args) throws IOException {
        /*
        Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
        Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
        Контролируем время выполнения: программа не должна загружаться дольше 10 секунд,
        а чтение – занимать свыше 5 секунд.
         */
        //RandomAccessFile -чтение с файла и запись в файл. Работает как с большим массивом данных
        String filePath = "src/file/";
       // File fileReadStory = null;
        int currentPage = 1;

        StringBuilder help = new StringBuilder();
        help.append("______________\n")
                .append("Открыть файл. Напишите open и Имя файла с расширением\n")
                .append("Введите номер страницы. Напишите page  и номер страницы \n")
                .append("_______ \n")
                .append("================= \n");
        System.out.println(help);
        Scanner scanner = new Scanner(System.in, "UTF-8");

        String userInput = null;
        String[] userCommand = null;

        //Создадим файл
        try(FileOutputStream fileStory = new FileOutputStream("src/file/Story.txt"))
        {
            for(int i = 1; i< 11500;i++){
                fileStory.write(randomFrom(65,105));
            }
        }
        //Сделаем цикл по флагу
        boolean flagForWhile = false;
        while (!flagForWhile)
        {
            userInput = scanner.nextLine();
            userCommand = userInput.split(" ");
            switch (userCommand[0]){
                case "open":
                    String fileName = filePath+userCommand[1];
                    File fileReadStory = new File(fileName);
                    if(fileReadStory.exists())
                    {
                        System.out.println("test!");
                        randomAccessFile = new RandomAccessFile(fileName,"r");
                        showPage(currentPage);
                    }
                    else {
                        System.out.println("Файл не найден");
                    }
                    break;
                case "help":
                    System.out.println(help);
                    break;
                case "page":
                    currentPage = Integer.parseInt(userCommand[1]);
                    break;
                case "exit":
                    flagForWhile = true;
                    break;
                default:
                    System.out.println("Введите help");
            }

        }


    }


    public static void showPage(int pageNumber) throws IOException {
        if(pageNumber==1) randomAccessFile.seek(pageNumber-1);
        else randomAccessFile.seek((pageNumber-1)*PAGE_SIZE);
        byte[] buffer = new byte[PAGE_SIZE];
        //Чтение в байт буфер
        randomAccessFile.read(buffer);
        //Вывод на экран строки
        System.out.println(new String(buffer,"UTF-8"));
    }
    private static int randomFrom(int min, int max) {
        //Число в диапазоне 10-15
        //(0...1)*5+10
        max = max - min;

        return (int) (Math.random() * max + min);
    }
}
