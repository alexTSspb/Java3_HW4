import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task2TestException {
    private Task2 task2;

    @Before
    public void startTest(){
        task2 = new Task2();
    }
    @Test(expected = RuntimeException.class)
    public void testException(){
        Assert.assertArrayEquals(task2.masConcerningFour(new int[]{1, 2, 3, 6, 7, 8, 9}), null);
    }
}
