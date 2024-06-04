package org.example.aop;

import org.example.aop.interceptor.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

public class ProxyMethodInvocation implements MethodInvocation{

    private final List<MethodInterceptor> interceptors;
    private int idx;
    private final Object originObj;
    private final Method originMethod;
    private final Object[] args;

    public ProxyMethodInvocation(List<MethodInterceptor> interceptors, Object originObj, Method originMethod, Object[] args) {
        this.interceptors = interceptors;
        this.originObj = originObj;
        this.originMethod = originMethod;
        this.args = args;
    }

    @Override
    public Object proceed() throws Throwable{
        if(idx >= interceptors.size()) {
            return executeOriginalMethod();
        }
        MethodInterceptor interceptor = interceptors.get(idx++);
        return interceptor.invoke(this);
    }

    private Object executeOriginalMethod() throws Throwable{
        originMethod.setAccessible(true);
        return originMethod.invoke(originObj, args);
    }
}