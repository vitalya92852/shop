package povtor.les2;

import java.util.*;

public class generic {
    public static void main(String[] args) {
        
        List<Integer> integerList = new ArrayList<>();

        Collections.addAll(integerList,123,1,3,-20,100);

        System.out.println(integerList);
        Collections.sort(integerList);
        System.out.println(integerList);

        List<String> stringList = new ArrayList<>();
        
        Collections.addAll(stringList,"list","bob","olov");



        System.out.println(stringList);

        System.out.println(stringList);


    }

//    private static <T extends Comparable<T>> void sort(List<T> list){
//        int listSize = list.size();
//
//
//        for(int i = 0;i<listSize;i++){
//            for(int j = 1+i;j<listSize;j++){
//                if(list.get(i).compareTo(list.get(j))>0){
//                    T save = list.get(i);
//                    list.set(i,list.get(j));
//                    list.set(j,save);
//                }
//            }
//        }
//    }
    private static <T extends Comparator<T>> void sort(List<T> list,Comparator<T> comparator){
        int listSize = list.size();

        for(int i = 0;i<listSize;i++){
            for(int j = 1+i;j<listSize;j++){
                if(comparator.compare(list.get(i),list.get(j))>0){
                    T save = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,save);
                }
            }
        }
    }
}
