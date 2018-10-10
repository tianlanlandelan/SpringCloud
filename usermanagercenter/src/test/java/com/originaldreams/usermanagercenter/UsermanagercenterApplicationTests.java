package com.originaldreams.usermanagercenter;

import com.originaldreams.usermanagercenter.controller.LogonController;
import com.originaldreams.usermanagercenter.controller.PermissionController;
import com.originaldreams.usermanagercenter.utils.MyRouterObject;
import org.junit.Test;
import java.lang.reflect.Method;
import java.util.List;

public class UsermanagercenterApplicationTests {
    @Test
    public  void printClassMethodMessage() {// 该类所属 的信息
        // 要获取类的信息，首先要获取类的类类型
        Class<?> class1 = new LogonController().getClass();// 传递的是哪个子类的对象，class1就是该子类的类类型
        // 获取类的名称
        System.out.println("类的名称是：" + class1.getSimpleName());
        /**
         * Method类，方法对象 一个成员方法就是一个Method对象 getMehtod()方法
         * 获取的是所有得public的函数，包括父类继承的 getDeclaredMethods()获取的是所有该类声明的方法，不同访问权限
         */
        Method[] ms = class1.getMethods();
        for (int i = 0; i < ms.length; i++) {
            // 得到方法的返回值类型的类类型
            Class<?> returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            // 得到方法的名称
            System.out.print(ms[i].getName() + "(");

            ms[i].getParameters();
            // 获取参数类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class2 : paramTypes) {
                System.out.print(class2.getName() + ",");
            }
            System.out.println(")");

        }
    }

//    @Test
//    public  void readRouters() {
//        List<MyRouterObject> list = RouterUtils.readRouters(LogonController.class,PermissionController.class);
//        for(MyRouterObject myRouterObject : list){
//            System.out.println(myRouterObject);
//        }
//    }
}
