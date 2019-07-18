import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Ex1 {
    public static void main(String[] args) throws IOException {
        //Создание массива
        long time = System.currentTimeMillis();
        System.out.println("Создание массива");
        try(FileOutputStream fileOutputStream = new FileOutputStream("src/file/Tempp.txt"))
        {
            for(int i = 65; i< 115;i++){
                fileOutputStream.write(i);
            }
        }
        System.out.println("Файл создан");
        //Буфер
        byte[] buffer = null;

        //Чтение файла
        try(FileInputStream fileInputStream = new FileInputStream("src/file/Tempp.txt"))
        {
            buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
        }
        System.out.println("Результат byte[] " + Arrays.toString(buffer));

        long time2 = System.currentTimeMillis()-time;
        System.out.println(time2);
     }
}
