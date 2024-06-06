package org.example.week3;
/**
 * Spring Boot bean scope
 *  1. Singleton
 *  2. Prototype
 *  3. Request
 *  4. Session
 *  5. Global Session
 */

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
