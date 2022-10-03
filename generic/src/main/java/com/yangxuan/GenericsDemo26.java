package com.yangxuan;

public class GenericsDemo26 {

    public static void main(String[] args) {
        // <?> 无限制通配符
        // <? extends E> extends 关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
        // <? super E> super 关键字声明了类型的下界，表示参数化的类型可能是指定的类型，或者是此类型的父类

        // 使用原则《Effective Java》
        // 为了获得最大限度的灵活性，要在表示 生产者或者消费者 的输入参数上使用通配符，使用的规则就是：生产者有上限、消费者有下限
        // 1. 如果参数化类型表示一个 T 的生产者，使用 < ? extends T>;
        // 2. 如果它表示一个 T 的消费者，就使用 < ? super T>；
        // 3. 如果既是生产又是消费，那使用通配符就没什么意义了，因为你需要的是精确的参数类型。
    }

}
