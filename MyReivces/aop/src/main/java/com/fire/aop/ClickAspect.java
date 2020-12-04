package com.fire.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ClickAspect {
  private static final String TAG = "ClickAspect";

  @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
  public void clickMethod(){
    Log.e("+++++", "点击成功");
  }

  @Around("clickMethod()")
  public void onClickMethodAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    View view = null;
    for (Object arg : args) {
      if (arg instanceof View) {
        view = (View) arg;
      }
    }
    //获取View 的 string id
    String resEntryName = null;
    String resName = null;
    if (view != null) {
      // resEntryName: btn_activity  resName: cn.appblog.aop_tech:id/btn_activity
      resEntryName = view.getContext().getResources().getResourceEntryName(view.getId());
      resName = view.getContext().getResources().getResourceName(view.getId());
    }
    joinPoint.proceed();
    Log.d("+++++", "after onclick: " + "resEntryName: " + resEntryName + "  resName: " + resName);
  }
}
