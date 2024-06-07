package org.example.aop;


import org.example.aop.advice.After;
import org.example.aop.advice.Around;
import org.example.aop.advice.Before;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyAOPExample {
    public static void main(String[] args) {
        AOPEmployee employee = (AOPEmployee) Proxy.newProxyInstance(
                MyAOPExample.class.getClassLoader(),
                new Class[]{AOPEmployee.class},
                new JdkAOPInvocationHandler(new AOPEmployeeImpl(), new AOPTestAspect())
        );
        employee.print();
    }
}
interface AOPEmployee {
    void print();
}
class AOPEmployeeImpl implements AOPEmployee {
    @Override
    public void print() {
        System.out.println("this is employee method");
    }
}
class AOPTestAspect {
    @Around
    public void around2(MethodInvocation mi) {
        System.out.println("this is around before2");
        try {
            mi.proceed();
        } catch (Throwable ex) {

        }
        System.out.println("this is around after2");
    }

    @Before
    public void before1() {
        System.out.println("this is before 1");
    }

    @After
    public void after1() {
        System.out.println("this is after 1");
    }

    @Before
    public void before2() {
        System.out.println("this is before 2");
    }

    @After
    public void after2() {
        System.out.println("this is after 2");
    }

    @Around
    public void around1(MethodInvocation mi) {
        System.out.println("this is around before1");
        try {
            mi.proceed();
        } catch (Throwable ex) {

        }
        System.out.println("this is around after1");
    }
}

