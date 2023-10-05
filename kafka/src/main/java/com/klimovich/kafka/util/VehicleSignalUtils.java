package com.klimovich.kafka.util;

import java.util.HashMap;

import com.klimovich.kafka.model.VehicleDistance;
import com.klimovich.kafka.model.VehicleSignal;

public class VehicleSignalUtils {

    private static HashMap<String, VehicleSignal> latestSavedSignal = new HashMap<>();
    private static HashMap<String, VehicleDistance> distanceTraveled = new HashMap<>();

    public static double distanceInKM(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to meters


        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public static void saveLatestSignal(VehicleSignal vehicleSignal){
        if (latestSavedSignal.containsKey(vehicleSignal.getId()) && !latestSavedSignal.get(vehicleSignal.getId()).equals(vehicleSignal)){
            latestSavedSignal.replace(vehicleSignal.getId(), latestSavedSignal.get(vehicleSignal.getId()), vehicleSignal);
        } else latestSavedSignal.putIfAbsent(vehicleSignal.getId(), vehicleSignal);
    }

    public static VehicleSignal getLatestSavedSignal(VehicleSignal vehicleSignal){
        return latestSavedSignal.get(vehicleSignal.getId());
    }

    public static void saveIfAbsent(VehicleSignal vehicleSignal){
        latestSavedSignal.putIfAbsent(vehicleSignal.getId(), vehicleSignal);
    }

    public static void saveIfAbsent(VehicleDistance vehicleDistance){
        distanceTraveled.putIfAbsent(vehicleDistance.getId(), vehicleDistance);
    }

    public static void addToDistanceTraveled(VehicleDistance vehicleDistance){
        VehicleDistance latestSavedDistance = distanceTraveled.get(vehicleDistance.getId());
        vehicleDistance.setDistance(vehicleDistance.getDistance() + latestSavedDistance.getDistance());
        distanceTraveled.replace(vehicleDistance.getId(), latestSavedDistance,  vehicleDistance);
    }

}
