import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class Ex2 {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        //Последовательно сшить 5 файлов в один.

        //Этап 1. Создание имен файлов.
        ArrayList<String> files = new ArrayList<>();
        for (int i = 0; i < 5; i++) files.add("TestFile" + (i+1) + ".txt");
        //Этап 2. Создание файлов
        for(String one: files)
        {
            try(FileOutputStream fileOutputStream = new FileOutputStream("src/file/"+one))
            {
                for(int i = 0 ;i< 100;i++){
                    fileOutputStream.write(randomFrom(65,115));
                }
                fileOutputStream.write('\n');
            }
        }
        //Этап 3. Создадим как в подсказке массив файловых потоков
        ArrayList<FileInputStream> fileInputStreams = new ArrayList<>();
        //Этап 4. Инициализуруем массив
        for(String one:files){
            fileInputStreams.add(new FileInputStream("src/file/"+one));
        }
        //Этап 5. Создадим перечисление на базе массива как в подсказке к ДЗ
        //Enumeration
        Enumeration<FileInputStream> fileInputStreamEnumeration = Collections.enumeration(fileInputStreams);
        //Этап 6. Создать последовательность из перечисления
        SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStreamEnumeration);
        //Этап 7.Создадим исходящий файловый поток и запишем туда все
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/file/OutputFile.txt")){
            int onebyte;
            while ((onebyte = sequenceInputStream.read() )!= -1){
                fileOutputStream.write(onebyte);
            }
        }
        //
        File file = new File("src/file/OutputFile.txt");
        if(file.exists()){
            System.out.println(file.length());
        }

    }

    private static int randomFrom(int min, int max) {
        //Число в диапазоне 10-15
        //(0...1)*5+10
        max = max - min;

        return (int) (Math.random() * max + min);
    }
}