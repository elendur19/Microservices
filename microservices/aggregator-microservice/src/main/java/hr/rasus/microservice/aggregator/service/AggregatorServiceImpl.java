package hr.rasus.microservice.aggregator.service;

import hr.rasus.microservice.aggregator.RestTemplateImplementation;
import hr.rasus.microservice.aggregator.ex.WrongTemperatureUnitException;
import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.model.HumidityRepresentation;
import hr.rasus.microservice.aggregator.model.TemperatureRepresentation;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AggregatorServiceImpl {

    private final RestTemplateImplementation restTemplateImplementation;

    public AggregatorServiceImpl(RestTemplateImplementation restTemplateImplementation) {
        this.restTemplateImplementation = restTemplateImplementation;
        temperatureUrl = null;
        humidityUrl = null;
        temperatureUnit = null;
    }
    @Value("${temperature-url}")
    private final String temperatureUrl;

    @Value("${humidity-url}")
    private final String humidityUrl;

    @Value("${temperatureUnit}")
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