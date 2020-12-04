package com.fire.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TimeMethodAspect {

  @Pointcut("execution(@com.fire.aop.TimeSpeed * *(..))")
  public void methodTime(){}

  @Around("methodTime()")
  public Object timeJoinPoint(ProceedingJoinPoint point) throws Throwable{
    MethodSignature signature = (MethodSignature) point.getSignature();
    String className = signature.getDeclaringType().getSimpleName();
    String methodName = signature.getName();
    String funName = signature.getMethod().getAnnotation(TimeSpeed.class).value();

    //统计事件
    long startTime = System.currentTimeMillis();
    Object result = point.proceed();
    long endTime = System.currentTimeMillis() - startTime;
    Log.e("+++++", "名字= "+className+"  方法名字= "+
      methodName+"  反射方法入参= "+funName+"  开始时间= "+startTime+" 结束时间= "+endTime);
    return result;
  }
}
