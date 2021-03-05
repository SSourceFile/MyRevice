package com.fire.myreivces.ui.designMod.java;

import android.util.Log;

public class Animal implements Fly, Run{

  private final String t = "++++";
  @Override
  public void fly() {
    Log.e(t, "animal fly");
  }

  @Override
  public void run() {
    Log.e(t, "animal run");
  }
}
