# Smart Environment Monitor

A Java-based Smart Environment Monitoring application that processes sensor data, calculates environmental statistics, detects abnormal temperature readings, and generates a JSON report.


**GitHub Repository:** 
https://github.com/arifulatwork/smart-environment-monitor

## Features

- Reads environmental data from multiple sensors.
- Calculates average temperature and humidity.
- Detects abnormal temperature readings.
- Displays monitoring results in the terminal.
- Generates a JSON report using Jackson.
- Uses Apache Commons CSV for data processing.

## Technologies

- Java 21
- Apache Maven
- JUnit 5
- Apache Commons CSV
- Jackson

## Prerequisites

- JDK 21 or later
- Apache Maven 3.8 or later

Check your installation:

```bash
java -version
mvn -version
```

## Build the project

Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd smart-environment-monitor
```

Build and verify:

```bash
mvn clean verify
```

## Run the application

```bash
java -jar target/smart-environment-monitor-1.0.0.jar
```

## Run tests

```bash
mvn test
```

## Generated report

After execution, the application generates:

`target/environment-report.json`

The report contains:
- Total number of sensors
- Average temperature
- Average humidity
- Abnormal sensor readings

## Example output

```text
SMART ENVIRONMENT MONITOR

Total sensors: 5
Average temperature: 25.34 C
Average humidity: 61.60 %

Abnormal temperature readings:
WARNING: SENSOR_03 temperature is 31.50 C

JSON report generated: target/environment-report.json
```

## Project version

Current version: `1.0.0`

## License

Academic project developed for EMSE Build Tools coursework.
