

/**
 * 除了显示调用 ClassLoader.loadClass 进行加载 Class 之外，JVM 在下面的5种场景下，也会执行加载 Class 的操作（由 JVM 调用 ClassLoader.loadClassInternal）
 *
 * 使用 new 关键字实例化对象的时候、读取或设置一个类的静态字段（被final修饰、已在编译期把结果放入常量池的静态字段除外）的时候，以及调用一个类的静态方法的时候。
 * 使用 java.lang.reflect 包的方法对类进行反射调用的时候，如果类没有进行过初始化，则需要先触发其初始化。
 * 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 * 当虚拟机启动时，用户需要指定一个要执行的主类（包含 main()方法的那个类），虚拟机会先初始化这个主类。
 * 当使用 JDK 1.7 的动态语言支持时，如果一个 java.lang.invoke.MethodHandle 实例最后的解析结果 REF_getStatic、REF_putStatic、REF_invokeStatic 的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。
 *
 * 以上几种加载时机，统称为主动引用的方式；除此之外，其他引用类的方式都不会被触发 Class 的加载
 */

/**
 * Reflection
 * MethodHandle
 * ClassLoader
 * Class
 * VM
 */
public class NotInitialization {
    public static void main(String[] args) {
        //根据场景1，读取的是常量，不会造成类的初始化
        System.out.println(ConstClass.HELLOWORLD);
    }
}

class ConstClass {

    static final String HELLOWORLD = "hello world";

    static {
        System.out.println("ConstClass init!");
    }
}

