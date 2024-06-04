package org.example.aop.interceptor;


import org.example.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterMethodInterceptor implements MethodInterceptor{

    private final Object aspectObj;
    private final Method aspectMethod;

    public AfterMethodInterceptor(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        Object result = mi.proceed();
        aspectMethod.setAccessible(true);
        aspectMethod.invoke(aspectObj);
        return result;
    }
}
