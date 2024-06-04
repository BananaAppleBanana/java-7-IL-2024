package org.example.aop.interceptor;


import org.example.aop.MethodInvocation;

public interface MethodInterceptor {
    Object invoke(MethodInvocation mi) throws Throwable;
}
