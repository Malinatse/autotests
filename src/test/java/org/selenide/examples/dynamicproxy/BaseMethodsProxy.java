package org.selenide.examples.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Dynamic Proxy pattern implementation
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class BaseMethodsProxy implements InvocationHandler {

    private final SeleniumWrapper obj;

    public static SeleniumWrapper newInstance(SeleniumWrapper obj) {
        return (SeleniumWrapper) java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new BaseMethodsProxy(obj));
    }

    private BaseMethodsProxy(SeleniumWrapper obj) {
        this.obj = obj;
    }

    /**
     * Добавление проверок на существование и кликаемость перед действиеи над web элементом
     */
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            result = m.invoke(obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }
        return result;
    }
}
