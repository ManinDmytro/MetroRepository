package Metro;

import java.util.LinkedList;

public class Station {

	String name;

	LinkedList<Passenger> waitingPassengers;

	public Station(String name) {
		this.name = name;
		this.waitingPassengers = new LinkedList<Passenger>();
	}

	@Override
	public String toString() {
		return "Station [(" + name + "); wait(" + waitingPassengers.size() + ")]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Passenger> getWaitingPassengers() {
		return waitingPassengers;
	}

	public void setWaitingPassengers(LinkedList<Passenger> waitingPassengers) {
		this.waitingPassengers = waitingPassengers;
	}

}
