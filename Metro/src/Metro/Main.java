package Metro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		CollectionMetro metro = new CollectionMetro();

		metro.createWagons();
		metro.createTrains();

		System.out.println(metro.trains);
		metro.createLines();
		metro.createLineTrains();
		System.out.println("-----------------------");

		metro.manageDriversQueue();
		metro.printPriorityQueue(metro.driverQueue);
		System.out.println("-----------------------");
		metro.createLineStations();

		metro.passengersInOutTrains();
		System.out.println("-----------------------");
		metro.showPasssengersLeft();

	}

}
