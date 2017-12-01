package com.concurrency.diningphilosophor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {

	private int id;
	private Lock lock;
	
	public Chopsticks(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}
	
	public boolean pickUp(Philosopher philosopher,State state){
		
		try {
			if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
				System.out.println(philosopher+" got "+state.toString()+" "+this);
				return true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean putDown(Philosopher philosopher, State state){
		lock.unlock();
		System.out.println(philosopher+" putdown the "+state.toString()+" "+this);
		return true;
	}
	@Override
	public String toString() {
		return "chopstick-"+ id;
	}
}
