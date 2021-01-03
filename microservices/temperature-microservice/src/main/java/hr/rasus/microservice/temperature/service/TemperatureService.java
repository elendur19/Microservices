package hr.rasus.microservice.temperature.service;


import hr.rasus.microservice.temperature.model.TemperatureRepresentation;

public interface TemperatureService {
    TemperatureRepresentation getTemperature();
}
