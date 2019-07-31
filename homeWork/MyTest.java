package homeWork;

public class MyTest {
    @BeforeSuite
    public static void startTest(){
        System.out.println("Start...");
    }

//    @BeforeSuite
//    public static void startTest2(){
//        System.out.println("Start...");
//    }

    @Test(priority = -1)
    public static void step1(){
        System.out.println("test1() не попадет в тест");
    }
    @Test(priority = 1)
    public static void step2(){
        System.out.println("step 2 =- priority1");
    }
    @Test(priority = 8)
    public static void step3(){
        System.out.println("step 3 =- priority8");
    }
    @Test(priority = 6)
    public static void step4(){
        System.out.println("step 4 =- priority5");
    }
    @Test(priority = 7)
    public static void step5(){
        System.out.println("step 5 =- priority7");
    }
    @Test()
    public static void step6(){
        System.out.println("step 6 =- DEFAULT");
    }

    @AfterSuite
    public static void endSuite(){
        System.out.println(".......END");
    }

}
