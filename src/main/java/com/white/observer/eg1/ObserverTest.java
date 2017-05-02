package com.white.observer.eg1;

import com.white.observer.eg1.impl.ConcreteWatched;
import com.white.observer.eg1.impl.ConcreteWatcher;

public class ObserverTest {

	public static void main(String[] args) {
		//实例化被观察者
		Watched girl = new ConcreteWatched();
		
		Watcher boy1 = new ConcreteWatcher("boy1");
		Watcher boy2 = new ConcreteWatcher("boy2");
		Watcher boy3 = new ConcreteWatcher("boy3");
		
		girl.addWatcher(boy1);
		girl.addWatcher(boy2);
		girl.addWatcher(boy3);
		
		girl.notifyWatcher("girl is happy");
	}
	
}
