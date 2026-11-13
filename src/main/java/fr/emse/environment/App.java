package fr.emse.environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        Path csvFile = args.length > 0
                ? Path.of(args[0])
                : Path.of("data/sensors.csv");

        CsvImporter importer = new CsvImporter();

        EnvironmentAnalyzer analyzer = new EnvironmentAnalyzer();

        List<SensorReading> readings = importer.importReadings(csvFile);

        double averageTemperature =
                analyzer.calculateAverageTemperature(readings);

        double averageHumidity =
                analyzer.calculateAverageHumidity(readings);

        List<SensorReading> abnormalReadings =
                analyzer.findAbnormalReadings(readings);

        System.out.println("==================================");
        System.out.println("   SMART ENVIRONMENT MONITOR");
        System.out.println("==================================");

        System.out.println("Total sensors: " + readings.size());

        System.out.printf(
                "Average temperature: %.2f C%n",
                averageTemperature
        );

        System.out.printf(
                "Average humidity: %.2f %%%n",
                averageHumidity
        );

        System.out.println("\nAbnormal temperature readings:");

        if (abnormalReadings.isEmpty()) {
            System.out.println("No abnormal readings detected.");
        } else {
            abnormalReadings.forEach(reading ->
                    System.out.printf(
                            "WARNING: %s temperature is %.2f C%n",
                            reading.sensorId(),
                            reading.temperature()
                    )
            );
        }

        Map<String, Object> report = new LinkedHashMap<>();

        report.put("totalSensors", readings.size());
        report.put("averageTemperature", averageTemperature);
        report.put("averageHumidity", averageHumidity);
        report.put("abnormalReadings", abnormalReadings);

        Path reportPath = Path.of("target/environment-report.json");

        Files.createDirectories(reportPath.getParent());

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(reportPath.toFile(), report);

        System.out.println("\nJSON report generated: " + reportPath);
    }
}
