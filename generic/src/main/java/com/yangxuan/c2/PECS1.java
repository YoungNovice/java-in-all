package com.yangxuan.c2;

/**
 * https://juejin.cn/post/6875510280898478088
 * 现在PESC原则（Producer Extends Consumer Super），就很好理解了。
 * <p>
 * 频繁往外读取内容的适合：上界通配符<? extends T>
 * 频繁往里插入的，设立：下界通配符<? super T>
 */
public class PECS1 {

    public static void main(String[] args) {
    }

    public static void a() {
        // super T [Object, T]
        // extend T [T, Child]
        // Plate<? extends Fruit> p = new Plate<Apple>(new Apple()); // error

        /*
         报错
         * 编译器认定的逻辑是这样的：
         苹果 is a 水果
         * 装苹果的盘子 is not a 装水果的盘子
         所以就算容器中装的东西有继承关系，但容器之间是没有继承关系的。
         所以我们不可以让装水果的盘子Plate<Fruit>的引用指向装苹果的盘子Plate<Apple>

         所以为了让泛型用起来更舒服，Sun公司就想出来<? extends T>和<? super T>的办法，
         来让“水果盘子”和“苹果盘子”之间有关系。
         */

        /*
         Plate<? extends Fruits>
         翻译出来就是：一个能放水果和所有水果派生类的盘子。再直白点就是：啥水果都能放的盘子。

         Plate<? extends Fruit>和Plate<Apple>
         最大的区别就是：Plate<? extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类。
         直接的好处就是，我们可以用“苹果盘子”给“水果盘子”赋值了。类似于向上转型。
         */
        Plate<? extends Fruit> p = new Plate<>(new Apple());
        /*
         Plate<? extends Fruits> 一个能放水果以及一切是水果基类（父类）的盘子。
        Plate<? super Fruit>是Plate<Fruit>的基类（上界通配符和下界通配符都是Plate<T>的基类），
        但不是Plate<Apple>的基类。

        边界让Java不同泛型之间的转换更容易了，但这样的转换也有一定的副作用，那就是容器的部分功能可能失效。主要是“存”和“取”元素。
         */
    }

    /**
     * 上界通配符<? extends Fruit>不能向容器中存东西，只能（有限制地）往外取东西
     * 取东西只能用T接收或者T的基类接收。
     * 1.<? extends Fruit>会使往盘子里放东西的set()方法失效。但取东西get()方法有效。比如下面例子中的俩个
     * set()方法，插入Apple和Fruit都报错。
     * <p>
     * 2.原因是编译器只知道容器内是Fruit或者它的派生类，所以取时不能用具体的派生类接收。只能用Fruit或者Fruit的
     * 基类接收。
     * <p>
     * 所以通配符<?>和类型参数<T>的区别就在于，对于编译器来说所有的T都代表同一种类型。比如下面这个泛型方法里，
     * 三个T都指代同一个类型，要么都是String，要么都是Integer。
     * public <T> List<T> fill(T... t);
     * <p>
     * 3.但通配符<?>没有这种约束，Plate<?>单纯的就表示：盘子里放了一个东西，是什么我不知道。
     * <p>
     * 所以存操作的问题就出现在这里：上界通配符Plate<? extends Fruit>里面放什么不确定，都是Fruit的派生类。存
     * 操作时，接收却只是用了一个占位符或者通配符来接收，来表示捕获一个Fruit类或其子类，具体什么类不知道。然后无论
     * 是想往里面插入Apple或者Meat或者Fruit编译器都不知道能不能和这个占位符匹配，所以存操作都不允许。
     * 所以存操作失效，什么都放不进去。
     * <p>
     * 取操作只能当做Fruit 由于父类可以直接引用子类对象可以 Fruit到Object的都可以
     */
    public static void b() {
        Plate<? extends Fruit> p = new Plate<>(new Apple());

        //存入元素操作都会报错
        p.set(new Fruit()); //error
        p.set(new Apple()); //error

        //读元素操作只能放在Fruit或它的基类中
        // 他能确定是一个Fruit 自动转型机制那么一定可以是Fruit的父类 但是是不是Apple不能确定
        Fruit newFruit1 = p.get();
        Food food = p.get();
        Object newFruit2 = p.get();

        //这样取会报错
        Apple newFruit3 = p.get(); //Error
    }

    /**
     * 1.理解一下：下界通配符Plate<? super Fruit>意味着容器中存的都是Fruit和所有Fruit的基类。那么此时取出来的东
     * 西也只可能是Fruit或Fruit的基类，但是具体是哪个类不知道，所以只能用Object接收。所以<? super Fruit>会使从盘子
     * 里取东西的get()方法部分失效，只能存放到Object对象里面。
     * <p>
     * 2.但是set()存操作正常。
     * 因为下界规定了元素的最小粒度的下限，实际上是放松可容器元素的类型控制。因为存放的元素都是Fruit的基类，那只
     * 要往里面存的粒度都比Fruit小或等都可以。但往外读取元素就费劲了，只有所有类的基类Object对象才能保证每次都装下。
     * 但是这样的话，元素的类型信息就全部丢失了。
     */
    public static void c() {
        Plate<? super Fruit> p = new Plate<>(new Fruit());

        //存入元素正常
        p.set(new Fruit());
        p.set(new Apple());

        //读取出来的东西只能存放在Object类里
        Apple newFruit3 = p.get(); //error
        Fruit newFruit1 = p.get(); //error

        Object newFruit2 = p.get();
    }


}
