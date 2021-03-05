package com.fire.myreivces.ui.compose;

import com.fire.myreivces.ui.designMod.java.Animal;
import com.fire.myreivces.ui.designMod.java.Fly;
import com.fire.myreivces.ui.designMod.java.ProxyHandler;

import java.lang.reflect.Proxy;

public class ProxyUtils {


  public void showOne(){
    Animal animal = new Animal();
    //获取类加载器
    ClassLoader classLoader = animal.getClass().getClassLoader();
    Class<?>[] interfaces = animal.getClass().getInterfaces();
    ProxyHandler proxyHandler = new ProxyHandler(animal);
    Object newInv = Proxy.newProxyInstance(classLoader, interfaces, proxyHandler);
    Fly fy = (Fly) newInv;
    fy.fly();
  }
}
