package com.white.observer.eg2.impl;

import com.white.observer.eg2.DisplayElement;
import com.white.observer.eg2.Observer;
import com.white.observer.eg2.Subject;

/**
 * 布告板上的状态显示
 */
public class ConditionDisplay implements Observer,DisplayElement{
    private float temp;
    @SuppressWarnings("unused")
	private Subject weatherStation;

    public ConditionDisplay(Subject weatherStation) {
        // 构造时需要间主题/被观察者对象作为注册之用
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    @Override
    public void display() {
        // 将数据显示在布告板上
        System.out.println("布告板显示当前温度为：" + temp + "℃");
    }

    @Override
    public void update(float temp) {
        // 接受来自主题/被观察者（气象站）的数据
        this.temp = temp;
        display();
    }
}