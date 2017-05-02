package com.white.observer.eg1;
/**
 * 抽象的主题角色，即为抽象的被观察者，声明方法（添加、移除观察者，通知观察者）
 * @author bailf
 *
 */
public interface Watched {

	public void addWatcher(Watcher watcher);
	
	public void removeWatcher(Watcher watcher);
	
	public void notifyWatcher(String str);
	
}
