package com.yangxuan.c2;

import java.util.ArrayList;
import java.util.List;

class Plate<T> {
    T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}

class Food{}

class Fruit extends Food {}
class Meat extends Food {}

class Apple extends Fruit {}
class Banana extends Fruit {}
class Pork extends Meat{}
class Beef extends Meat{}

class RedApple extends Apple {}
class GreenApple extends Apple {}

/**
 * 上界<? extends T>不能往里存，只能往外取，适合频繁往外面读取内容的场景。
 * 下界<? super T>不影响往里存，但往外取只能放在Object对象里，适合经常往里面插入数据的场景。
 */
public class Comma1 {

    public static void main(String[] args) {
        // extends 可读不可写
        Plate<? extends Food> p = new Plate<>(new Food());
        // p.set(new Fruit()); // error
        // Food f = p.get(); // ok

        // super 可写不可读
        Plate<? super Food> s = new Plate<>(new Food());
        // Food f = s.get(); // error
        // s.set(new Fruit()); // ok
        // Object object = s.get(); // ok


        List<? super CharSequence> list = new ArrayList<>();
        list.add("s");
        Object o = list.get(0);
        System.out.println(o);

        // 简单理解
        // 如果限制写 用super 不可读
        // 如果限制读 用extends 不可写
    }


}

/*
 * 无界通配符 意味着可以使用任何对象，因此使用它类似于使用原生类型。但它是有作用的，原生类型可以持有任何类型，
 * 而无界通配符修饰的容器持有的是某种具体的类型。举个例子，在List<\?>类型的引用中，不能向其中添加Object, 而List类型的引用就可以添加Object类型的变量。
 * 最后提醒一下的就是，List<\Object>与List<?>并不等同，List<\Object>是List<?>的子类。还有不能往List<?> list里添加任意对象，除了null。
 *
 */
