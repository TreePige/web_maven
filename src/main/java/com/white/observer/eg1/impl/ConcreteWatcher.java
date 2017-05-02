package com.white.observer.eg1.impl;

import com.white.observer.eg1.Watcher;
/**
 * 定义具体的观察者
 * @author bailf
 *
 */
public class ConcreteWatcher implements Watcher {

	/**
	 * 当前观察者的名字
	 */
	private String name = null;
	
	public ConcreteWatcher() {
		super();
	}
	public ConcreteWatcher(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update(String str) {
		System.out.println(name+" watches "+str);
	}

}
