package Metro;

import java.util.LinkedList;

public class Line {

	String name;

	// List of train lines
	LinkedList<Train> trainsInLine;

	// List of station lines
	LinkedList<Station> stationInLine;

	public Line(String name) {
		this.name = name;
		stationInLine = new LinkedList<Station>();
	}

	@Override
	public String toString() {
		return "Line [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Train> getTrainsInLine() {
		return trainsInLine;
	}

	public void setTrainsInLine(LinkedList<Train> trainsInLine) {
		this.trainsInLine = trainsInLine;
	}

	public LinkedList<Station> getStationInLine() {
		return stationInLine;
	}

	public void setStationInLine(LinkedList<Station> stationInLine) {
		this.stationInLine = stationInLine;
	}

}
