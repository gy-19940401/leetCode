package test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import po.User;

import java.lang.reflect.Method;

/**
 * @Description: cglib 代理
 * @author: GanYang
 * @Date: 2022/10/25 21:04
 */
public class CglibProxy {

    public static void main(String[] args) {
//        methodProxy();

        methodProxyBase();
    }

    private static void methodProxyBase() {
        Enhancer proxy = new Enhancer();
        proxy.setSuperclass(User.class);
        proxy.setCallback(new MethodInterceptor() {

            /**
             *
             * @param o             cglibProxyObject
             * @param method        originalMethod
             * @param objects       param
             * @param methodProxy   methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName());
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println(invoke);
                return invoke;
            }
        });

        User user = (User) proxy.create();

        System.out.println(user.getId());
    }

    private static void methodProxy() {
        User user = new User(1, "一川烟雨", "12312312");

        User proxy = new MethodInterceptor() {

            public User createProxy() {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(User.class);
                enhancer.setCallback(this);
                return (User) enhancer.create();
            }

            /**
             *
             * @param o            cglibProxy
             * @param method       original method
             * @param objects      param
             * @param methodProxy  methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("AllInstance");
                Object invoke = method.invoke(user, objects);
                System.out.println("Instance");
                return invoke;
            }
        }.createProxy();

        System.out.println(proxy.getId());
    }


}
