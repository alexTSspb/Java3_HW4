import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class Task3Test {
    @Parameterized.Parameters

    public static Collection<Object[]> collection(){
        return Arrays.asList(
                new Object[][]{
                        {new int[]{1,1,1,4,4,4,4}, true},
                        {new int[]{1,6,1,1,1,4}, false},
                        {new int[]{4,4,4,4,4,4}, false},
                }
        );
    }
    private int[] data;
    private boolean result;

    public Task3Test(int[] data, boolean result){
        this.data = data;
        this.result = result;
    }
    private Task3 task3;

    @Before
    public void startTest(){
        task3 = new Task3();
    }
    @Test
    public void testWork(){
        Assert.assertEquals(task3.ConsistByOneAndFour(data), result);

    }

}
