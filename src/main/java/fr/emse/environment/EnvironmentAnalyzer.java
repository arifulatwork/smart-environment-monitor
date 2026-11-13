package fr.emse.environment;

import java.util.List;

public class EnvironmentAnalyzer {

    private static final double TEMPERATURE_THRESHOLD = 30.0;

    public double calculateAverageTemperature(List<SensorReading> readings) {

        if (readings.isEmpty()) {
            throw new IllegalArgumentException("No sensor readings available");
        }

        return readings.stream()
                .mapToDouble(SensorReading::temperature)
                .average()
                .orElseThrow();
    }

    public double calculateAverageHumidity(List<SensorReading> readings) {

        if (readings.isEmpty()) {
            throw new IllegalArgumentException("No sensor readings available");
        }

        return readings.stream()
                .mapToDouble(SensorReading::humidity)
                .average()
                .orElseThrow();
    }

    public List<SensorReading> findAbnormalReadings(
            List<SensorReading> readings) {

        return readings.stream()
                .filter(r -> r.temperature() > TEMPERATURE_THRESHOLD)
                .toList();
    }
}
