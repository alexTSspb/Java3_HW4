public class HW4 {
    //Обобщим задачу, дается любая строка "...."  котору. надо посимвольно напечатать n раз
    private static String sequence;
    //Поток
    private static class MyThread implements Runnable{
        //Создадим объект для синхронизации
        private static final Object MONITOR = new Object();
        private int time;//количество повторений
        private static char primalChar = sequence.charAt(0);//в начале primalChar - 1 элемент строки
        private  char firstChar;//Текущий символ для вывода на экран
        private  char secondChar;//следующий за ним символ

        private MyThread(int index, int time){
            this.time = time;
            this.firstChar = sequence.charAt(index);
            this.secondChar = (index+1)<sequence.length()? sequence.charAt(index+1) : primalChar;//обеспечиваем цикличность
        }
        @Override
        public void run() {
            synchronized (MONITOR){
                for (int i = 0; i < this.time;i++){
                    //изначально primalChar = 1 элемент строки,
                    //поток уходит в ожидание пока primalChar не станет равен
                    //переданному по индексу элементу(для которого и открыт поток)
                    //Работать бкдет только тот поток,чья очередь
                    while (primalChar!= this.firstChar){//
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(primalChar);
                    primalChar = this.secondChar;
                    MONITOR.notifyAll();
                    //Для визуализации в консоли - пауза
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    public static void main(String[] args) {
        sequence = "abc";
        int time = 5;
        for(int i = 0, size = sequence.length();i < size; i++) {
            new Thread(new MyThread(i,time)).start();
        }
    }
}