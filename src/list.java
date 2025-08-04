import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class list {
    public static void main(String [] args) {
        List<Integer> l = List.of(1,2,3,4,5);
        Integer[] arr = l.toArray(new Integer[l.size()]); //list转换为array
        for(Integer i : arr){
            System.out.println(i);
        }
        List<Integer> l1 =List.of(arr);//array转换为list

        l1.add(6); //List.of()创建的list是不可变的，不能添加元素 是只读list
        for(Integer i : l1){
            System.out.println(i);
        }

    }
}
