package Metro;

public class Passenger {

	String name;

	public Passenger(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + "]";
	}
}
