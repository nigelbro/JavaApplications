package car.distance;

import java.util.ArrayList;

public class Sort {
	
	static ArrayList<Car> leftPartition;
	static ArrayList<Car> rightPartition;
	private Car pivotCar;
	
	public Sort(ArrayList<Car> carsToSort){
		leftPartition = new ArrayList<Car>();
		rightPartition = new ArrayList<Car>();
		this.pivotCar = carsToSort.get(carsToSort.size() /2);
	
	}
	
	
	public void partition(ArrayList<Car> listToPartition,int pivot){
		
		long startTime = System.currentTimeMillis();
		int left = pivot-1;
		int right = pivot+1;
		
		while(left >=0 && right <= listToPartition.size()-1){
			
			Car leftCar = listToPartition.get(left);
			Car rightCar = listToPartition.get(right);
			
			if (Car.CarPropertiesComparator.compare(pivotCar,leftCar) == -1){
				
				rightPartition.add(leftCar);
				
			}else if (Car.CarPropertiesComparator.compare(pivotCar,leftCar) == 1){
				
				leftPartition.add(leftCar);
			}
			
			 if (Car.CarPropertiesComparator.compare(pivotCar,rightCar) == -1){
				 
				rightPartition.add(rightCar);
				
			}else if (Car.CarPropertiesComparator.compare(pivotCar,rightCar) == 1){
				
				leftPartition.add(rightCar);
			}
			 
			left--;
			right++;
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Partitioning complete....Time:" +(endTime-startTime)+" miliseconds\n");
	}
	
	public void sortLeftPartition(ArrayList<Car> left,int index){
		
			
					long startTime = System.currentTimeMillis();
					int start = index;
					int end = left.size();
					int s = start;
					
					if (end-start >=1){
						
						while(s < end){
							
							if(Car.CarPropertiesComparator.compare(left.get(start),left.get(s)) == 1){
								ArrayList<Car> newList = new ArrayList<Car>();
								newList.addAll(swapCar(left,start,s));
								leftPartition.clear();
								leftPartition.addAll(newList);
							}
							
							s++;
						}
						
						start++;
						sortLeftPartition(leftPartition,start);
						
					}else{
						leftPartition.add(pivotCar);
						long endTime = System.currentTimeMillis();
						System.out.println("Left sort complete....Time:" +(endTime-startTime)+" miliseconds\n");
					}	
	}
	
	public void sortRightPartition(ArrayList<Car> right,int index){
		
				
				long startTime = System.currentTimeMillis();
				int start = index;
				int end = 0;
				int s = start;
				
				if (start - end >=1){
					
					while(s > end){
				
						if(Car.CarPropertiesComparator.compare(right.get(start),right.get(s)) == -1){
							
							ArrayList<Car> newList = new ArrayList<Car>();
							newList.addAll(swapCar(right,start,s));
							rightPartition.clear();
							rightPartition.addAll(newList);
						}
						
						s--;
					}
					
					start--;
					sortRightPartition(rightPartition,start);
					
				}else{
					
					long endTime = System.currentTimeMillis();
					System.out.println("Right sort complete....Time:" +(endTime-startTime)+" miliseconds\n");
				}
	}
	
	private ArrayList<Car> swapCar(ArrayList<Car> list,int carOne, int  carTwo){
			
			Car temp1 = list.get(carOne);
			Car temp2 = list.get(carTwo);
			list.remove(carOne);
			list.add(carOne,temp2);
			list.remove(carTwo);
			list.add(carTwo, temp1);
			return list;
	}
	
	public void printResults(ArrayList<Car> left, ArrayList<Car> right){
		
		System.out.println("Pivot: "+ pivotCar.getDestination()+" "+pivotCar.getColor()+" "+pivotCar.getSerialNumber());
		System.out.println("\nPrinting  sorted results\n");
				Thread l = new Thread(){
					public void run(){
						
						
						for(Car car : left){
					
							System.out.println(car.getDestination() + "   "+ car.getColor() + "   "+ car.getSerialNumber() +"\n");
					
						}
					}
				};
				
				l.start();
				
				Thread r = new Thread(){
					
					public void run(){
						
						for(Car car : right){
							
							System.out.println(car.getDestination() + "   "+ car.getColor() + "   "+ car.getSerialNumber() +"\n");
					
						}
					}
					
				};
				
				while(l.isAlive()){
					
				}
					r.start();
				/*	try {
						r.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				*/
				
		
	}
}
