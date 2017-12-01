package com.concurrency.diningphilosophor;

import java.util.Random;

public class Philosopher implements Runnable{

	private int id;
	private Chopsticks leftChopstick;
	private Chopsticks rigthChopstick;
	private State state;
	private Random random;
	private volatile boolean isFull = false;
	private int eatingCounter=0;
	
	
	public Philosopher(int id, Chopsticks leftChopstick,
			Chopsticks rigthChopstick, State state) {
		super();
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rigthChopstick = rigthChopstick;
		this.state = state;
		this.random = new Random();
	}


	@Override
	public void run() {

		while(!isFull){
			try{
				think();
				
				if(leftChopstick.pickUp(this, State.LEFT)){
					if(rigthChopstick.pickUp(this, State.RIGHT)){
						eat();
						rigthChopstick.putDown(this, State.RIGHT);
					}
					rigthChopstick.putDown(this, State.LEFT);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void setIsFull(boolean isFull){
		this.isFull = isFull;
	}
	
	public void think() throws InterruptedException{
		System.out.println(this+" is thinking");
		Thread.sleep(random.nextInt(1000));
	}
	
	public void eat() throws InterruptedException{
		System.out.println(this+" eating..");
		this.eatingCounter++;
		Thread.sleep(random.nextInt(1000));
	}
	
	public int getEatCounter(){
		return this.eatingCounter;
	}
	@Override
	public String toString() {
		return "Philosopher "+id;
	}
}
