import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightPlanner {
    private static final String FLIGHT_DATA_FILE = "flights.txt";

    private Map<String, ArrayList<String>> availableFlights;
    private List<String> citiesInRoute;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FlightPlanner flightPlanner = new FlightPlanner();
        flightPlanner.run();
    }

    private void run() {
        printWelcomeMessage();
        populateCitiesFromDatabase();
        printAvailableCities();
        planRoute();
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Flight Planner!");
    }

    private void populateCitiesFromDatabase() {
        availableFlights = new HashMap<String, ArrayList<String>>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FLIGHT_DATA_FILE))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split("->");
                String origin = splitLine[0].trim();
                String destination = splitLine[1].trim();

                ArrayList<String> destinations = availableFlights.getOrDefault(origin, new ArrayList<String>());
                destinations.add(destination);

                availableFlights.put(origin, destinations);
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private void printAvailableCities() {
        System.out.println("Here's a list of all the cities in our database:");

        for (String city : availableFlights.keySet()) {
            System.out.println(String.format(" %s", city));
        }
    }

    private void planRoute() {
        String startingCity = getStartingCity();
        citiesInRoute = new ArrayList<String>();
        citiesInRoute.add(startingCity);

        while (true) {
            String nextCity = getNextCity();
            citiesInRoute.add(nextCity);

            if (nextCity.equals(startingCity)) {
                printRoute();
                break;
            }
        }
    }

    private String getStartingCity() {
        System.out.println("Let's plan a round-trip route!");
        return getCityFromUser("Enter the starting city: ");
    }

    private String getCityFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private String getNextCity() {
        String origin = citiesInRoute.get(citiesInRoute.size() - 1);

        while (true) {
            printDestinationsForCity(origin);
            String nextCity = getCityFromUser(String.format("Where do you want to go from %s? ", origin));

            if (availableFlights.get(origin).contains(nextCity)) {
                return nextCity;

            } else {
                System.out.println("You can't get to that city by a direct flight.");
            }
        }
    }

    private void printDestinationsForCity(String origin) {
        System.out.println(String.format("From %s you can fly directly to:", origin));

        for (String destination : availableFlights.get(origin)) {
            System.out.println(String.format(" %s", destination));
        }
    }

    private void printRoute() {
        System.out.println("The route you've chosen is:");
        System.out.println(String.join(" -> ", citiesInRoute));
    }
}
