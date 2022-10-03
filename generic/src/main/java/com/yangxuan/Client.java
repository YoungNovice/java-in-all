package com.yangxuan;


import java.util.Iterator;
import java.util.List;

//多个限制使用&符号
public class Client {

    //要进行比较，所以 E 需要是可比较的类，因此需要 extends Comparable<…>
    //Comparable< ? super E> 要对 E 进行比较，即 E 的消费者，所以需要用 super
    //参数 List< ? extends E> 表示要操作的数据是 E 的子类的列表，指定上限，这样容器才够大
    private <E extends Comparable<? super E>> E max(List<? extends E> e1) {
        if (e1 == null) {
            return null;
        }
        //迭代器返回的元素属于 E 的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }

    //工资低于2500元的上斑族并且站立的乘客车票打8折
    /*public static <T extends Staff & Passenger> void discount(T t) {
        if (t.getSalary() < 2500 && t.isStanding()) {
            System.out.println("恭喜你！您的车票打八折！");
        }
    }

    public static void main(String[] args) {
        discount(new Me());
    }*/
}
