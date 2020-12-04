package com.fire.myreivces.aspect;

import android.util.Log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodAspect {

  @Pointcut("call(* com.fire.myreivces.aspect.AspectActivity.onCreate(..))")
  public void callMethod(){
    Log.e("++++", "毁掉了");
  }
}
