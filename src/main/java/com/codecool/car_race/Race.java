package com.codecool.car_race;

import com.codecool.car_race.vehicles.Motorcycle;
import com.codecool.car_race.vehicles.Truck;
import com.codecool.car_race.vehicles.Vehicle;

import java.util.LinkedList;
import java.util.List;

public class Race {
    private Weather weather = new Weather();
    static final int NUM_OF_LAPS = 50;
    private final List<Vehicle> vehicles = new LinkedList<>();
    private boolean brokenTruck;
    /**
     * Simulates the passing of time by advancing the weather and
     * moving the vehicles for the duration of a whole race.
     */
    public void simulateRace() {
        for (int i = 0; i < NUM_OF_LAPS; i++) {
            for (Vehicle vehicle : vehicles) {
                vehicle.prepareForLap(this);
                vehicle.moveForAnHour();
            }

            // change weather and update broken truck status after the movement done
            weather.randomize();
            brokenTruck = getBrokenTruckStatus();
        }

    }

    /**
     * Prints the state of all vehicles. Called at the end of the
     * race.
     */
    public void printRaceResults() {
        System.out.println("Race results:");
        for(Vehicle racer: vehicles){
            System.out.println(racer);
        }
    }

    public void registerRacer(Vehicle racer) {
        vehicles.add(racer);
    }

    public boolean isRaining() {
        return weather.isRaining();
    }
    public boolean isYellowFlagActive() {
        return brokenTruck;
    }
    private boolean getBrokenTruckStatus() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                if (truck.isBrokenDown()) {
                    return true;
                }
            }
        }
        return false;
    }
}
