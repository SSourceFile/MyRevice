package com.fire.myreivces.ui.designMod.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

  private Animal animal;
  public ProxyHandler(Animal animal){
    this.animal = animal;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return method.invoke(animal, args);
  }
}
