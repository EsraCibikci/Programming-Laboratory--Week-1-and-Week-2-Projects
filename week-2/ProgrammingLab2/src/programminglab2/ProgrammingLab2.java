package programminglab2;

import java.util.Scanner;

public class ProgrammingLab2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter the number of stops on the route: ");
        int totalStops = scanner.nextInt();
        scanner.nextLine(); 

        String[] stopNames = new String[totalStops];
        int[] boarding = new int[totalStops];
        int[] alighting = new int[totalStops];
        int[] occupancy = new int[totalStops];

        for (int i = 0; i < totalStops; i++) {
            System.out.println("\n--- " + (i + 1) + ". STOP INFORMATION ---");
            System.out.print("Stop name: ");
            stopNames[i] = scanner.nextLine();

            System.out.print("Number of passengers boarding: ");
            boarding[i] = scanner.nextInt();

            System.out.print("Number of passengers alighting: ");
            alighting[i] = scanner.nextInt();
            scanner.nextLine(); 
        }

        System.out.print("\nEnter the bus's seating capacity: ");
        int seatingCapacity = scanner.nextInt();

        
        int currentPassengers = 0;
        int overCapacityCount = 0;

        for (int i = 0; i < totalStops; i++) {
            currentPassengers += boarding[i];

            
            if (alighting[i] > currentPassengers) {
                System.out.println("Data entry error at " + stopNames[i]
                        + ": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                currentPassengers = 0;
            } else {
                currentPassengers -= alighting[i];
            }

            occupancy[i] = currentPassengers;

            
            if (currentPassengers > seatingCapacity) {
                System.out.println("Warning: Bus is over capacity at " + stopNames[i] + "!");
                overCapacityCount++;
            }
        }

        
        System.out.println("\n================ ROUTE SUMMARY: ================");
        for (int i = 0; i < totalStops; i++) {
            System.out.printf("Stop: %-15s | Boarding: %-3d | Alighting: %-3d | Current occupancy: %-3d%n",
                    stopNames[i], boarding[i], alighting[i], occupancy[i]);
        }

       
        int busiestStopIndex = 0;
        int maxBoarding = boarding[0];
        double totalOccupancySum = 0;

        for (int i = 0; i < totalStops; i++) {
            if (boarding[i] > maxBoarding) {
                maxBoarding = boarding[i];
                busiestStopIndex = i;
            }
            totalOccupancySum += occupancy[i];
        }

        double averageOccupancy = totalOccupancySum / totalStops;

        System.out.println("\n================ STATISTICS ================");
        System.out.println("Busiest Stop: " + stopNames[busiestStopIndex] + " (" + maxBoarding + " passenger)");
        System.out.printf("Average occupancy between stops: %.2f%n", averageOccupancy);
        System.out.println("Number of stops where capacity is exceeded: " + overCapacityCount);

       
        int finalOccupancy = occupancy[totalStops - 1];
        if (finalOccupancy != 0) {
            System.out.println("Warning: " + finalOccupancy + " passengers still on the bus after the final stop — please check your data.");
        } else {
            System.out.println("All passengers disembarked at the final stop; the route was successfully completed..");
        }

       
    }
}
