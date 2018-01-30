package Metro;

import java.util.Random;

public class Driver {

	String name;
	int experience;
	Train currentTrain;

	public Driver(String name, int experience) {
		this.name = name;
		this.experience = experience;
	}

	public void recalculatingExp(int km) {
		Random rnd = new Random();
		this.experience = rnd.nextInt(km) - (km / 2);
	}

	public void doDrive() {
		recalculatingExp(50);
	}

	@Override
	public String toString() {
		return "Driver [name=" + name + ", experience=" + experience + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Train getCurrentTrain() {
		return currentTrain;
	}

	public void setCurrentTrain(Train currentTrain) {
		this.currentTrain = currentTrain;
		currentTrain.setDriver(this);
	}

}
