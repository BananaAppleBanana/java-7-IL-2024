package org.example.week3;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyClass {
    String value() default "abc";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyMethod {}


@MyClass(value = "123")
class EmployeeWeek3 {
    private int age;

    @MyMethod
    protected int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "EmployeeWeek3{" +
                "age=" + age +
                '}';
    }
}

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = EmployeeWeek3.class;
        EmployeeWeek3 emp = (EmployeeWeek3) clazz.getDeclaredConstructors()[0].newInstance();
        Field[] fields = clazz.getDeclaredFields();
//        System.out.println(Arrays.toString(fields));
        Method method = clazz.getDeclaredMethod("getAge", null);
//        System.out.println(Arrays.toString(methods));
        Field ageField = fields[0];
        ageField.setAccessible(true);
        ageField.set(emp, 5);
//        System.out.println(method.invoke(emp, null));
//        System.out.println(emp);
        System.out.println(Arrays.toString(method.getDeclaredAnnotations()));
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation annotation: annotations) {
            System.out.println(annotation);
            System.out.println(((MyClass)annotation).value());
        }
    }
}


