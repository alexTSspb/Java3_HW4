public class Task2 {
    /*

    Написать метод которому в качестве аргумента передается одномерный массив не пустой
    Массив должен вернуть новый массив чисел идущих после последней 4.
    Если в массиве исходном нет 4 , то Runtime Exception
    Написать тесты
     */
    int[] masConcerningFour(int[] source) {
        int[] result = null;
        int[] temp = new int[0];
        int lastElement = source.length - 1;
        //Если последний элемент  = 4, передаем пустой массив
        if (source[lastElement] == 4)
            return new int[0];
        for (int j = lastElement - 1; j >= 0; j--) {
            if (source[j] == 4) {
                result = new int[lastElement - j];
                System.arraycopy(source, j + 1, result, 0, result.length);
                return result;
            }

        }

        throw new RuntimeException();
    }
}