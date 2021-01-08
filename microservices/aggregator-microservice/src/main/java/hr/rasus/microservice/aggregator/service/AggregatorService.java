package hr.rasus.microservice.aggregator.service;

import hr.rasus.microservice.aggregator.RestTemplateImplementation;
import hr.rasus.microservice.aggregator.ex.ErrorResponse;
import hr.rasus.microservice.aggregator.ex.WrongTemperatureUnitException;
import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.model.HumidityRepresentation;
import hr.rasus.microservice.aggregator.model.TemperatureRepresentation;
import lombok.AllArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseExtractor;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private final RestTemplateImplementation restTemplateImplementation;

    public AggregatorService(RestTemplateImplementation restTemplateImplementation) {
        this.restTemplateImplementation = restTemplateImplementation;
        temperatureUrl = null;
        humidityUrl = null;
        temperatureUnit = null;
    }
    @Value("${temperature-url}")
    private final String temperatureUrl;

    @Value("${humidity-url}")
    private final String humidityUrl;

    @Value("${temperature-unit}")
    private String temperatureUnit;

    public AggregatedRepresentation getAggregatedMeasurements() {
        AggregatedRepresentation aggregatedRepresentation = new AggregatedRepresentation();
        TemperatureRepresentation temperatureRepresentation = restTemplateImplementation.getTemperatureRepresentation(temperatureUrl);

        HumidityRepresentation humidityRepresentation = restTemplateImplementation.getHumidityRepresentation(humidityUrl);
        // set temperature
        setAggregatedTemperature(aggregatedRepresentation, temperatureRepresentation.getTemperature());

        aggregatedRepresentation.setHumidity(humidityRepresentation.getHumidity());

        return aggregatedRepresentation;
    }

    @SneakyThrows
    private void setAggregatedTemperature(AggregatedRepresentation aggregatedRepresentation, double temperature) {

        if (temperatureUnit.equals("Celsius")) {
            // return temperature in Celsius
            aggregatedRepresentation.setTemperature(temperature);
        } else if (temperatureUnit.equals("Kelvin")) {
            aggregatedRepresentation.setTemperature(temperature + 273.15);
        } else {
            // wrong temperature format
            throw new WrongTemperatureUnitException("Temperature unit must have valid format!");
        }
    }

}