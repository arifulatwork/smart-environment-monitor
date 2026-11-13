package fr.emse.environment;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    public List<SensorReading> importReadings(Path file) throws IOException {

        List<SensorReading> readings = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .get();

        try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
             CSVParser parser = format.parse(reader)) {

            for (CSVRecord record : parser) {

                SensorReading reading = new SensorReading(
                        record.get("sensor_id"),
                        Double.parseDouble(record.get("temperature")),
                        Double.parseDouble(record.get("humidity"))
                );

                readings.add(reading);
            }
        }

        return readings;
    }
}
