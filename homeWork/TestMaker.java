package homeWork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class TestMaker {


    public static void start(Class testClass){
        try {
            testing(testClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void testing(Class testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] declaredMethods = testClass.getDeclaredMethods();
        //массив методов класса
        List<Method> invokeMethods = new ArrayList<>();
        System.out.printf("Тестирование класса %s%n",testClass.getName());
        Method beforeSuite = null;//для контроля единственности
        Method afterSuite = null;
        for (Method oneOfMeth : declaredMethods)
        {
            if(oneOfMeth.getDeclaredAnnotations().length==0)
                continue;

            if (oneOfMeth.isAnnotationPresent(BeforeSuite.class)){
                if(beforeSuite!= null)
                {
                    throw new RuntimeException("Аннотация BeforeSuite дублируется в классе");
                }
                beforeSuite = oneOfMeth;
                continue;
            }

            if(oneOfMeth.isAnnotationPresent(AfterSuite.class)){
                if(afterSuite!=null)
                    throw new RuntimeException("Аннотация AfterSuite дублируется в классе");

                afterSuite = oneOfMeth;
                continue;
            }

            if(oneOfMeth.isAnnotationPresent(Test.class)){
                int priority = oneOfMeth.getAnnotation(Test.class).priority();
                //Сделаем выборку по приоритетам
                if(priority>=1 && priority<=10)
                    invokeMethods.add(oneOfMeth);
            }

        }
        invokeMethods.sort((method1, method2)->{
            Integer priority1 = method1.getAnnotation(Test.class).priority();
            Integer priority2 = method2.getAnnotation(Test.class).priority();
            return Integer.compare(priority2,priority1);
        });
//
//        invokeMethods.sort(new Comparator<Method>() {
//            @Override
//            public int compare(Method o1, Method o2) {
//                return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
//            }
//        });

        if(beforeSuite!=null){
            invokeMethods.add(0,beforeSuite);
        }

        if(afterSuite!=null)
        {
            invokeMethods.add(afterSuite);
        }

        Object object = testClass.newInstance();

        for (Method oneOf : invokeMethods){
            oneOf.invoke(object);
        }
    }

}
