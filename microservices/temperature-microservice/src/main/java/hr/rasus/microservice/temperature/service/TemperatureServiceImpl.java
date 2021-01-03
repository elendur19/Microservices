package hr.rasus.microservice.temperature.service;

import hr.rasus.microservice.temperature.entity.Temperature;
import hr.rasus.microservice.temperature.repo.MeasurementRepo;
import hr.rasus.microservice.temperature.model.TemperatureRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TemperatureServiceImpl implements TemperatureService{

    private final MeasurementRepo measurementRepo;

    @Override
    public TemperatureRepresentation getTemperature() {
        TemperatureRepresentation temperatureRepresentation = new TemperatureRepresentation();
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        long id = 4 * hour + (minute / 15);
        Temperature measurement = measurementRepo.findById(id);
        temperatureRepresentation.setTemperature(measurement.getTemperature());
        return temperatureRepresentation;
    }
}
