import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
//Parameterized — довольно интересная запускалка, позволяет писать параметризированные тесты.
// Для этого в тест-классе объявляется статический метод возвращающий список данных,
// которые затем будут использованы в качестве аргументов конструктора класса.

@RunWith(value = Parameterized.class)
public class Task2Test {
    @Parameterized.Parameters
    public static Collection<Object[]> collection() {
        return Arrays.asList(
                new Object[][]{
                        {new int[]{4, 9, 8, 7, 6}, new int[]{9, 8, 7, 6}},
                        {new int[]{0, 4, 1, 7, 1}, new int[]{1, 7, 1}},
                        {new int[]{0, 0, 4, 5, 6}, new int[]{5, 6}},
                        {new int[]{0, 0, 0, 4, 7}, new int[]{7}},
                        {new int[]{0, 0, 0, 0, 4}, new int[]{} },
                        }
        );
    }
    private int[] data;
    private int[] result;

    public Task2Test(int[] data, int[] results) {
        this.data = data;
        this.result = results;
    }

    private Task2 task2;

    @Before
    public void startTest() {
        task2 = new Task2();
    }

    @Test
    public void testWork() {
        Assert.assertArrayEquals(task2.masConcerningFour(data), result);
    }
}
