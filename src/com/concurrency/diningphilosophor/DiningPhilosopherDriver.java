package com.concurrency.diningphilosophor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosopherDriver {

	public static void main(String[] args) {

		ExecutorService executreService = null;
		Philosopher[] philosophers = null;
		
		try{
			philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
			Chopsticks[] chopsticks = new Chopsticks[Constants.NUMBER_OF_CHOPSTICKS];
			
			for(int i=0; i<Constants.NUMBER_OF_CHOPSTICKS;++i)
				chopsticks[i] = new Chopsticks(i);
			
			executreService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
			
			for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;++i){
				philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1) % Constants.NUMBER_OF_CHOPSTICKS]);
				executreService.submit(philosophers[i]);
			}
				
			Thread.sleep(Constants.SIMULATION_TIMEOUT);
			
			for(Philosopher philosopher: philosophers){
				philosopher.setIsFull(true);
			}
		} catch (InterruptedException e) {
					e.printStackTrace();
		}finally{
			executreService.shutdown();
		}
		
		while(!executreService.isTerminated()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Philosopher philosopher : philosophers)
			System.out.println(philosopher+" eats - "+philosopher.getEatCounter()+" times");
	}

}
