package car.distance;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import car.distance.Main;
public class Car implements Comparable<Car>{
	
	private ArrayList<String> destinations =  new ArrayList<>();
	private ArrayList<String> colors =  new ArrayList<>();
	private int serialNumber;
	private String destination;
	private String color;
	
	
	public Car(){
		
		try{
			for (String colorsLine : Files.readAllLines(Paths.get("CarInfo/colors"))){
				for(String color: colorsLine.split(",")){
					colors.add(color);
				}
			}
			for (String destinationsLine : Files.readAllLines(Paths.get("CarInfo/destinations"))){
				for(String destination: destinationsLine.split(",")){
					destinations.add(destination);
				}
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		setDestination(destinations.get(new Random().nextInt(destinations.size())));
		setColor(colors.get(new Random().nextInt(colors.size())));
	}
	public void setSerialNumber(int serialNumber){
		this.serialNumber = serialNumber;
	}
	
	private void setDestination(String destination){
		this.destination= destination;
	}
	private void setColor(String color){
		this.color = color;
	}
	public String getDestination(){
		return this.destination;
	}
	public String getColor(){
		return this.color;
	}
	public int getSerialNumber(){
		return this.serialNumber;
	}
	
	/**
	 * Sorting criteria
	 * 
	 * LA > NO > NY 
	 * 
	 * IF destinations of Car1 & Car2 are ===
	 * BLUE > GREEN > RED
	 * 
	 *IF colors of Car1 & Car2 are ===
	 *sort by serial number
	 * 
	 */
	
	@Override
	public int compareTo(Car compareCar) {
		
		if(this.getSerialNumber() > compareCar.getSerialNumber())
			return 1;
		else if (this.getSerialNumber() < compareCar.getSerialNumber())
			return -1;
		else
			return 0;
	}
	
	
	public static Comparator<Car> CarPropertiesComparator = new Comparator<Car>(){
		
		public int compare(Car car1, Car car2){
			
			if(Main.destinationsMap.get(car1.getDestination()) == Main.destinationsMap.get(car2.destination))
				if(Main.colorsMap.get(car1.getColor()) == Main.colorsMap.get(car2.getColor()))
					return car1.compareTo(car2);
				else if(Main.colorsMap.get(car1.getColor()) > Main.colorsMap.get(car2.getColor()))
					return 1;
				else if(Main.colorsMap.get(car1.getColor()) < Main.colorsMap.get(car2.getColor()))
					return -1;
				else 
					return 0;
			else if(Main.destinationsMap.get(car1.getDestination()) > Main.destinationsMap.get(car2.destination))
				return 1;
			else if(Main.destinationsMap.get(car1.getDestination()) < Main.destinationsMap.get(car2.destination))
				return -1;
			else
				return 0;
			

		}
		
		
	};
	
	
	
	
}
