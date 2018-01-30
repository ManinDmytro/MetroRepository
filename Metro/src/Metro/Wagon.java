package Metro;

import java.util.LinkedList;

public class Wagon {

	public static final int MaxCapaityWagon = 20;

	public boolean isHeaderWagon;
	public String name;
	public String wagonNumber;
	int capacity;

	// List of passengers of the wagon
	LinkedList<Passenger> passengersInWagon;

	public Wagon(String name, boolean isHeaderWagon) {
		this.name = name;
		this.isHeaderWagon = isHeaderWagon;
		capacity = 0;
		passengersInWagon = new LinkedList<Passenger>();
	}

	public boolean isHeaderWagon() {
		return isHeaderWagon;
	}

	public void setHeaderWagon(boolean isHeaderWagon) {
		this.isHeaderWagon = isHeaderWagon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getWagonNumber() {
		return wagonNumber;
	}

	public void setWagonNumber(String wagonNumber) {
		this.wagonNumber = wagonNumber;
	}

	@Override
	public String toString() {
		return "Wagon [isHeaderWagon=" + isHeaderWagon + ", name=" + name + ", wagonNumber=" + wagonNumber + "]";
	}

}
