package Metro;

import java.util.LinkedList;

public class Train {

	public static final byte maxWagonInTrain = 7;

	public String name;
	public String number;
	public int size;

	Driver driver;
	
	public boolean readyToGo;

	// Train wagons
	public LinkedList<Wagon> wagonsTrain;

	public Train(String name, String number) {
		this.name = name;
		this.number = number;
		this.size = 0;
		wagonsTrain = new LinkedList<Wagon>();
		readyToGo = false;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	// Checking the first wagon
	public boolean hasHeaderWagon() {
		if (wagonsTrain.size() >= 1)
			if (wagonsTrain.getFirst().isHeaderWagon)
				return true;
		return false;
	}

	// Checking the last wagon
	public boolean hasTrailerWagon() {
		if (wagonsTrain.size() >= 1)
			if (wagonsTrain.getLast().isHeaderWagon)
				return true;
		return false;
	}

	// Adding a wagon to a train
	public void addWagonInTrain(Wagon wagon) {

		if (this.size < maxWagonInTrain) {
			if (wagon.isHeaderWagon & !hasHeaderWagon())
				wagonsTrain.addFirst(wagon);
			else {
				if (wagon.isHeaderWagon & !hasTrailerWagon())
					wagonsTrain.addLast(wagon);
				else {
					if (!hasHeaderWagon() || !hasTrailerWagon())
						wagonsTrain.add(wagon);
					else
						wagonsTrain.add(1, wagon);
				}
			}
			renumberWagons();
			this.size = this.wagonsTrain.size();
			readyToGo = hasHeaderWagon() & hasTrailerWagon() & (size == maxWagonInTrain);

		} else
			System.out.println("Can't add more wagons!");
	}

	// Numbering of wagons
	public void renumberWagons() {
		for (Wagon wgn : wagonsTrain) {
			wgn.setWagonNumber(this.number + "" + wagonsTrain.indexOf(wgn));
		}
	}

	@Override
	public String toString() {
		return "Train [name=" + name + ", number=" + number + ", size=" + size + ", readyToGo=" + readyToGo
				+ ", wagonsTrain=" + wagonsTrain + "]\n";
	}

}
