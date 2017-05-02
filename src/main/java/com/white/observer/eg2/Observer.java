package com.white.observer.eg2;

/**
 *  观察者
 */
public interface Observer {
    // 当气象站观测的天气发生变化时，主题会把参数值传给观察者
    public void update(float temp);
}