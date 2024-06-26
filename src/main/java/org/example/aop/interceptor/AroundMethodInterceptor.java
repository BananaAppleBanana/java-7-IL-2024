package org.example.aop.interceptor;

import org.example.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AroundMethodInterceptor implements MethodInterceptor{

    private final Object aspectObj;
    private final Method aspectMethod;

    public AroundMethodInterceptor(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        aspectMethod.setAccessible(true);
        return aspectMethod.invoke(aspectObj, mi);
    }
}


