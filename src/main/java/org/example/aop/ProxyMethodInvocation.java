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

/**
 *  ProxyMethodInvocation: instance1
 *  List: [Before1, After1, Before2, After2]
 *  Before1 -> Before2 -> original method -> After2 -> After 1
 *
 *      instance1.proceed();
 *          idx = 0, Before1.invoke(instance1)
 *          run before1 logic : System.out.print("this is before 1");
 *          instance1.proceed();
 *              idx = 1, After1.invoke(instance1)
 *              instance1.proceed();
 *                  idx = 2, Before2.invoke(instance1)
 *                  run before 2 logic: System.out.print("this is before 2");
 *                  instance1.proceed();
 *                      idx = 3, After2.invoke(instance1)
 *                      instance1.proceed();
 *                          idx = 4 => execute original function
 *                      run after2 logic
 *              run after1 logic
 *
 * @Component
 * A {
 *      @Async
 *      void print(){}
 *
 *
 *      void get() {
 *          print();  //multithreading or not?
 *      }
 * }
 * @Component
 * B {
 *      @Autowired
 *      private A a;
 *
 *      public void run() {
 *          a.print();
 *      }
 * }
 */