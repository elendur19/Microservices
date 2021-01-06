package hr.rasus.microservice.humidity.service;

import hr.rasus.microservice.humidity.model.HumidityRepresentation;
import hr.rasus.microservice.humidity.repo.HumidityRepo;
import hr.rasus.microservice.humidity.entity.Humidity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class HumidityServiceImpl implements HumidityService{

    private final HumidityRepo humidityRepo;

    @Override
    public HumidityRepresentation getHumidity() {
        HumidityRepresentation humidityRepresentation = new HumidityRepresentation();
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        long id = 4 * hour + (minute / 15) + 2;
        Humidity measurement = humidityRepo.findById(id);
        humidityRepresentation.setHumidity(measurement.getHumidity());

        return humidityRepresentation;
    }
}
