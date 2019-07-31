public class Task3 {
    /*
    Написать метод, который проверяет состав массива из 1 и 4.
    Если в нем нет хоть одной 1 или 4 , то метод вернет false.

     */
    boolean ConsistByOneAndFour(int[] source){
        boolean oneExist = false;
        boolean fourExist = false;

        for(int element: source){
            if(element != 1 && element != 4){
                return false;
            }
            if(element == 1){
                oneExist = true;
            }
            if(element == 4){
                fourExist = true;
            }
        }
        return oneExist && fourExist;
    }
}
