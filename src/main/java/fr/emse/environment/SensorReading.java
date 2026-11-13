package fr.emse.environment;

public record SensorReading(
        String sensorId,
        double temperature,
        double humidity
) {
    public SensorReading {
        if (sensorId == null || sensorId.isBlank()) {
            throw new IllegalArgumentException("Sensor ID is required");
        }

        if (!Double.isFinite(temperature)) {
            throw new IllegalArgumentException("Invalid temperature");
        }

        if (!Double.isFinite(humidity) || humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException("Invalid humidity");
        }
    }
}
