package com.white.observer.eg1.impl;

import java.util.ArrayList;
import java.util.List;

import com.white.observer.eg1.Watched;
import com.white.observer.eg1.Watcher;
/**
 * 具体的被观察者
 * @author bailf
 * @time 2017年4月28日 上午11:22:39
 * @version 1.0
 */
public class ConcreteWatched implements Watched {

	/**
	 * 存放观察者
	 */
	private List<Watcher> watchers = new ArrayList<Watcher>();
	
	@Override
	public void addWatcher(Watcher watcher) {
		watchers.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		watchers.add(watcher);
	}

	@Override
	public void notifyWatcher(String str) {
		for (Watcher watcher : watchers) {
			watcher.update(str);
		}
	}

}
