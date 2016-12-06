package car.distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Main {
	
	static HashMap<String,Integer> colorsMap = new HashMap<String,Integer>();
	static HashMap<String,Integer> destinationsMap = new HashMap<String,Integer>();
	public static void main(String[] args) {
		 colorsMap.put("RED", 0);
		 colorsMap.put("GREEN", 1);
		 colorsMap.put("BLUE", 2);
		 destinationsMap.put("New York", 0);
		 destinationsMap.put("New Orleans", 1);
		 destinationsMap.put("Los Angeles", 2);
		ArrayList<Car> cars = new ArrayList<>();
		int counter = 0;
		System.out.println("Now creating list of cars....Time:" +System.currentTimeMillis()+"\n");
		while(counter < 1000){
			Car car = new Car();
			car.setSerialNumber(counter);
			cars.add(car);
			counter++;
		}
		System.out.println("Finished creating list of cars....Time:" +System.currentTimeMillis()+"\n");
		System.out.println("Now starting sorting...Time:"+System.currentTimeMillis()+ "\n");
		Sort sort = new Sort(cars);
		int pivot = cars.size() /2;
		long startTime = System.currentTimeMillis();
		sort.partition(cars, pivot);
		System.out.println("Back to Main Thread Lets Sort Our Left And Right Partitions");
		Thread leftSort = new Thread(){
			
			public void run(){
				sort.sortLeftPartition(Sort.leftPartition, 0);
			}
		};
		leftSort.start();
		
		Thread rightSort = new Thread(){
			
			public void run(){
				sort.sortRightPartition(Sort.rightPartition, Sort.rightPartition.size() -1);
			}
		};
		rightSort.start();
		
		try {
			leftSort.join();
			rightSort.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Sort Completed! : "+(endTime-startTime)+" ms");
		//sort.printResults(Sort.leftPartition, Sort.rightPartition);
	
	}
	
	

}
