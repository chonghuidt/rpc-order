package com.lp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther lp
 * @date 2020/6/20 0020 16:00
 */
public class Mediator {
    //用来存储发布服务的实例,map相当于服务调用的路由
    public static Map<String,BeanMethod> map = new ConcurrentHashMap<>();

    private volatile static Mediator instance;

    private Mediator(){};

    public static Mediator getInstance(){
        if(instance==null){
            synchronized (Mediator.class){
                if(instance==null){
                    instance=new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest request){
        String key=request.getClassName()+"."+request.getMethodName(); //key
        BeanMethod beanMethod=map.get(key);
        if(beanMethod==null){
            return null;
        }
        Object bean=beanMethod.getBean();
        Method method=beanMethod.getMethod();
        try {
            return method.invoke(bean,request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
