package com.white.observer.eg2.impl;

import java.util.ArrayList;
import java.util.List;

import com.white.observer.eg2.Observer;
import com.white.observer.eg2.Subject;

/**
 * 气象站实现主题，发布气象信息（气温）
 */
public class WeatherStation implements Subject{
    /**
     * 存放观察者
     */
	private List<Observer> observers = new ArrayList<Observer>();
    private float temp;

    public WeatherStation() {
        // 加个ArrayList存放所有注册的Observer对象;
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        // 当新的观察者注册时添加进来
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        // 当观察者取消注册时去除该观察者
        int i = observers.indexOf(o);
        if (i>=0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        // 更新状态，调用Observer的update告诉观察者有新的信息
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temp);
        }
    }

    /*
     *  此方法用于气象站收到的数据,并且调用更新使数据实时通知给观察者
     */
    public void setMeasurements(float temp){
        this.temp = temp;
        System.out.println("气象站测量的温度为：" + temp + "℃");
        notifyObserver();
    }
}