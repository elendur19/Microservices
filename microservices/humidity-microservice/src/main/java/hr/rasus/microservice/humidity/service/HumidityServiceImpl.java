package hr.rasus.microservice.humidity.service;

import hr.rasus.microservice.humidity.model.HumidityRepresentation;
import hr.rasus.microservice.humidity.repo.HumidityRepo;
import hr.rasus.microservice.humidity.entity.Humidity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HumidityServiceImpl implements HumidityService{

    private final HumidityRepo humidityRepo;

    @Value("${temperatureUnit}")
    private String temperatureUnit;

    private HumidityServiceImpl(HumidityRepo humidityRepo) {
        this.humidityRepo = humidityRepo;
        this.temperatureUnit = null;
    }

    @Override
    public HumidityRepresentation getHumidity() {
        HumidityRepresentation humidityRepresentation = new HumidityRepresentation();
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        long id = 4 * hour + (minute / 15);
        Humidity measurement = humidityRepo.findById(id);
        humidityRepresentation.setHumidity(measurement.getHumidity());

        return humidityRepresentation;
    }
}
