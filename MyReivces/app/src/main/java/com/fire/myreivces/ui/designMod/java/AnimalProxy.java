package com.fire.myreivces.ui.designMod.java;

import android.util.Log;

public class AnimalProxy implements Fly, Run{

  private final String t = "++++++";
  private Animal animal;

  public AnimalProxy(Animal animal){
    this.animal = animal;
  }

  @Override
  public void fly() {
    Log.e(t, "proxy --- fly");
    animal.fly();
  }

  @Override
  public void run() {
    Log.e(t, "proxy ---- run");
    animal.run();
  }
}
