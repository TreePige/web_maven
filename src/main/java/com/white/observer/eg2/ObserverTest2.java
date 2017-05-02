package com.white.observer.eg2;

import com.white.observer.eg2.impl.ConditionDisplay;
import com.white.observer.eg2.impl.WeatherStation;

public class ObserverTest2 {
	public static void main(String[] args) {
        // 首先创建一个主题/被观察者
        WeatherStation weatherStation = new WeatherStation();
        // 创建观察者并将被观察者对象传入
        @SuppressWarnings("unused")
		ConditionDisplay conditionDisplay = new ConditionDisplay(weatherStation);

        // 设置气象站模拟收到的气温数据
        weatherStation.setMeasurements(25);
        weatherStation.setMeasurements(24);
        weatherStation.setMeasurements(23);
    }
}
