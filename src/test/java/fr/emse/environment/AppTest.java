package fr.emse.environment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private final EnvironmentAnalyzer analyzer =
            new EnvironmentAnalyzer();

    @Test
    void testAverageTemperature() {

        List<SensorReading> readings = List.of(
                new SensorReading("S1", 20, 50),
                new SensorReading("S2", 30, 60)
        );

        assertEquals(
                25.0,
                analyzer.calculateAverageTemperature(readings),
                0.001
        );
    }

    @Test
    void testAverageHumidity() {

        List<SensorReading> readings = List.of(
                new SensorReading("S1", 40, 50),
                new SensorReading("S2", 30, 70)
        );

        assertEquals(
                60.0,
                analyzer.calculateAverageHumidity(readings),
                0.001
        );
    }

    @Test
    void testAbnormalTemperatureDetection() {

        List<SensorReading> readings = List.of(
                new SensorReading("S1", 22, 50),
                new SensorReading("S2", 35, 60),
                new SensorReading("S3", 28, 55)
        );

        List<SensorReading> abnormal =
                analyzer.findAbnormalReadings(readings);

        assertEquals(1, abnormal.size());
        assertEquals("S2", abnormal.get(0).sensorId());
    }

    @Test
    void testInvalidHumidity() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new SensorReading("S1", 22, 120)
        );
    }
}
