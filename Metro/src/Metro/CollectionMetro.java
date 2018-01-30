package Metro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class CollectionMetro {
	int maxWagonInDepo = 80;
	// Depot with wagons
	LinkedList<Wagon> depoWagon;
	// Trains
	HashSet<Train> trains;

	Line redLine, blueLine, greenLine;
	Queue<Driver> driverQueue;
	Map<String, LinkedList<Passenger>> passengersInStation;
	Comparator<Driver> comparator;
	ArrayList<Line> metroKiev;

	Random rnd;

	public CollectionMetro() {
		rnd = new Random();
		metroKiev = new ArrayList<>();
		depoWagon = new LinkedList<Wagon>();
		trains = new LinkedHashSet<Train>();
		passengersInStation = new HashMap<>();
	}

	public void createWagons() {
		for (int i = 0; i < maxWagonInDepo; i++) {
			Wagon wgn = new Wagon("Wagonnn" + i, (rnd.nextInt(100) < 40));
			depoWagon.add(wgn);
		}
	}

	public void createTrains() {
		int maxTrainsInDepo = depoWagon.size() / Train.maxWagonInTrain;

		for (int i = 0; i < maxTrainsInDepo; i++) {
			Train train = new Train("Train " + i, "00" + i);
			while (!depoWagon.isEmpty()) {
				train.addWagonInTrain(depoWagon.poll());
				if (train.size == Train.maxWagonInTrain) {
					break;
				}
			}
			if (train.readyToGo) {
				trains.add(train);
			}
		}
	}

	public void createLines() {
		redLine = new Line("Red");
		blueLine = new Line("Blue");
		greenLine = new Line("Green");

		metroKiev.add(redLine);
		metroKiev.add(blueLine);
		metroKiev.add(greenLine);
	}

	public void createLineTrains() {
		redLine.setTrainsInLine(new LinkedList<Train>());
		blueLine.setTrainsInLine(new LinkedList<Train>());
		greenLine.setTrainsInLine(new LinkedList<Train>());

		Iterator<Train> iter = trains.iterator();

		while (iter.hasNext()) {
			redLine.trainsInLine.add(iter.next());
			if (iter.hasNext())
				blueLine.trainsInLine.add(iter.next());
			if (iter.hasNext())
				greenLine.trainsInLine.add(iter.next());
		}
	}

	public void printPriorityQueue(Queue<Driver> queue) {
		Queue<Driver> temp;
		temp = new PriorityQueue<Driver>(10, comparator);
		for (Driver e : queue) {
			temp.add(e);
		}

		System.out.println("---------------Result Queue---------------------");
		while (!temp.isEmpty())
			System.out.println(temp.poll());
	}

	public void manageDriversQueue() {
		comparator = new Comparator<Driver>() {
			@Override
			public int compare(Driver o1, Driver o2) {

				if (o1.getExperience() > o2.getExperience()) {
					return -1;
				}
				if (o1.getExperience() < o2.getExperience()) {
					return 1;
				}
				return 0;

			}
		};

		driverQueue = new PriorityQueue<Driver>(10, comparator);

		driverQueue.add(new Driver("Konstantin", 8));
		driverQueue.add(new Driver("Kryvorukov I.I.", 29));
		driverQueue.add(new Driver("Petrovich", 56));
		driverQueue.add(new Driver("Stazher Seryozha", 6));

		// Print the initial state of the queue
		printPriorityQueue(driverQueue);

		Driver currentDriver = null;

		for (Train trn : trains) {
			while (!driverQueue.isEmpty()) {
				currentDriver = driverQueue.poll();
				currentDriver.setCurrentTrain(trn);
				currentDriver.doDrive();
				driverQueue.add(currentDriver);
				break;
			}
		}
	}

	// Adding stations to the line
	public void createLineStations() {

		for (int i = 0; i < 10; i++) {
			redLine.stationInLine.add(new Station(redLine.name + "Station " + i));
			blueLine.stationInLine.add(new Station(blueLine.name + "Station " + i));
			greenLine.stationInLine.add(new Station(greenLine.name + "Station " + i));
		}
	}

	// Creation of passengers and the launch of all trains through stations
	public void passengersInOutTrains() {
		for (Line line : metroKiev) {
			System.out.println(line.getName() + " : ");
			Iterator<Train> iterLine = line.getTrainsInLine().iterator();
			while (iterLine.hasNext()) {
				Train train = iterLine.next();
				System.out.println(" " + train.name + " <====> " + train.driver);
				runTrainForLine(line, train);
			}
		}
	}

	public void runTrainForLine(Line line, Train train) {
		Random rnd = new Random();
		int cntToOperate = 0, resultOper = 0;

		for (Station station : line.stationInLine) {
			cntToOperate = rnd.nextInt(70);
			// With the arrival of the train, several stations appear on each station
			for (int i = 0; i < cntToOperate; i++) {
				station.waitingPassengers.add(new Passenger("Passenger " + rnd.nextInt(1000)));
			}
			System.out.println("  " + station + "->");

			// Passengers leave the train
			for (Wagon wgn : train.wagonsTrain) {
				System.out.print("  " + wgn.name + "[" + wgn.passengersInWagon.size() + "] :  ");
				resultOper = 0;
				// If the wagon is not empty
				if (!wgn.passengersInWagon.isEmpty()) {
					// Check if there are passengers in the wagon
					Iterator<Passenger> passIter = wgn.passengersInWagon.iterator();
					cntToOperate = rnd.nextInt(10);
					// Several passengers leave
					while (passIter.hasNext() && cntToOperate > 0) {
						wgn.passengersInWagon.removeFirst();
						cntToOperate--;
						resultOper++;
					}
					System.out.print("   " + resultOper + " out; ");
				}

				// Passengers from the station board the train
				resultOper = 0;
				while (wgn.passengersInWagon.size() < Wagon.MaxCapaityWagon & station.waitingPassengers.size() > 0) {

					wgn.passengersInWagon.add(station.waitingPassengers.getFirst());
					station.waitingPassengers.removeFirst();
					resultOper++;
				}
				System.out.print("" + resultOper + " in; ");
				System.out.println("");
			}
		}
		System.out.println("");
	}

	// Add the remaining passengers to HashMap for quick search and printing the list of passengers
	public void showPasssengersLeft() {

		for (Line line : metroKiev) {
			for (Station station : line.stationInLine) {
				passengersInStation.put(station.name, station.waitingPassengers);
			}

		}

		System.out.println("Passengers at end station of Red line:");
		for (Passenger pass : passengersInStation.get("RedStation 9")) {
			System.out.println(" " + pass.name);
		}

		System.out.println("Passengers at end station of Blue line:");
		for (Passenger pass : passengersInStation.get("BlueStation 9")) {
			System.out.println(" " + pass.name);
		}
		System.out.println("Passengers at end station of Green line:");
		for (Passenger pass : passengersInStation.get("GreenStation 9")) {
			System.out.println(" " + pass.name);
		}

	}
}