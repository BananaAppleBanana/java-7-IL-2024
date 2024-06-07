package org.example.week3;
/**
 * Spring Boot bean scope
 *  1. Singleton
 *  2. Prototype
 *  3. Request
 *  4. Session
 *  5. Global Session
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  Singleton
 */
class EagerLoadingSingleton {
    private final static EagerLoadingSingleton instance = new EagerLoadingSingleton();

    private EagerLoadingSingleton() {}

    public static EagerLoadingSingleton getInstance() {
        return instance;
    }

}

class LazyLoadingSingleton {
    private static volatile LazyLoadingSingleton instance;

    private LazyLoadingSingleton() {}

    public static LazyLoadingSingleton getInstance() {
        if(instance == null) {
            synchronized (LazyLoadingSingleton.class) {
                if(instance == null) {
                    instance = new LazyLoadingSingleton();
                }
            }
        }
        return instance;
    }
}

enum EnumSingleton {
    INSTANCE;
}


/**
 *  Builder
 */
class StudentBuilder1 {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public StudentBuilder1 setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentBuilder1 setAge(int age) {
        this.age = age;
        return this;
    }


    @Override
    public String toString() {
        return "StudentBuilder1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        StudentBuilder1 stu = new StudentBuilder1().setAge(5).setName("abc");
        System.out.println(stu);

        Student2 stu2 = new StudentBuilder2().setAge(5).setName("abc").build();
        System.out.println(stu2);
    }
}
class Student2 {
    private String name;
    private int age;

    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class StudentBuilder2 {
    private String name;
    private int age;

    public StudentBuilder2 setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder2 setAge(int age) {
        this.age = age;
        return this;
    }
    public Student2 build() {
        return new Student2(name, age);
    }
}

/**
 * factory
 */
class MyStudentFactory {
    public static Student2 getStudent(String name, int age) {
        return new Student2(name, age);
    }
}
/**
 *  Composition
 *  Strategy vs Bridge
 */
interface CalculatorService {
    int run(int x, int y);
}
class CalculatorImplStrategy {
    public void calculate(CalculatorService calculatorService, int x, int y) {
        System.out.println(calculatorService.run(x, y));
    }
}
class CalculatorImplBridge {
    private CalculatorService calculatorService;
}
/**
 * adaptor
 */
interface Human {
    void print();
}
interface Robot {
    void print();
}
class HumanRobotAdaptor implements Robot {
    private final Human human;

    public HumanRobotAdaptor(Human human) {
        this.human = human;
    }

    @Override
    public void print() {
        human.print();
    }
}
class AdaptorTest {
    public static void main(String[] args) {
        Robot robot = new HumanRobotAdaptor(() -> System.out.println("i'm human"));
        robot.print();
    }
}
/**
 * facade
 *  switch() {
 *      case xx:
 *          instance1 execute data;
 *          break;
 *      case yy:
 *          instance2 execute data;
 *          break;
 *  }
 */

/**
 * static proxy
 *      inheritance
 */
class StaticProxyImpl1 extends CalculatorImplStrategy {
    @Override
    public void calculate(CalculatorService calculatorService, int x, int y) {
        System.out.println("before");
        super.calculate(calculatorService, x, y);
        System.out.println("after");
    }
}


interface AInterface {
    int get();
    void print();
}
class AImpl implements AInterface {
    @Override
    public int get() {
        return 0;
    }

    @Override
    public void print() {
        System.out.println("this is a print");
    }
}
class StaticProxyImpl2 implements AInterface {
    private final AInterface instance;

    public StaticProxyImpl2(AInterface instance) {
        this.instance = instance;
    }

    @Override
    public int get() {
        System.out.println("before");
        int res = instance.get();
        System.out.println("after");
        return res;
    }

    @Override
    public void print() {

    }
}


/**
 * dynamic proxy
 */
class DynamicProxyTest {
    public static void main(String[] args) {
        AInterface a = (AInterface) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class[]{AInterface.class},
                new MyInvocationHandler(new AImpl())
        );
        System.out.println(a.get());
        a.print();
    }
}

class MyInvocationHandler implements InvocationHandler {
    private final Object instance;

    public MyInvocationHandler(Object instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println(method);
        Object res = method.invoke(instance, args);
        System.out.println("after");
        return res;
    }
}



