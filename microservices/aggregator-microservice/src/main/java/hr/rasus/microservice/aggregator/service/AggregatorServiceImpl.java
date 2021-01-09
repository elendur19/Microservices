package hr.rasus.microservice.aggregator.service;

import hr.rasus.microservice.aggregator.RestTemplateImplementation;
import hr.rasus.microservice.aggregator.ex.WrongTemperatureUnitException;
import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.model.HumidityRepresentation;
import hr.rasus.microservice.aggregator.model.TemperatureRepresentation;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;


@Service
public class AggregatorServiceImpl {

    private final RestTemplateImplementation restTemplateImplementation;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${name.temperature}")
    private final String temperatureName;

    @Value("${name.humidity}")
    private final String humidityName;

    @Value("${temperatureUnit}")
    private String temperatureUnit;

    public AggregatorServiceImpl(RestTemplateImplementation restTemplateImplementation) {
        this.restTemplateImplementation = restTemplateImplementation;
        temperatureName = null;
        humidityName = null;
        temperatureUnit = null;
    }

    public AggregatedRepresentation getAggregatedMeasurements() {
        AggregatedRepresentation aggregatedRepresentation = new AggregatedRepresentation();
        String temperatureUri = discoveryClient.getInstances(temperatureName).get(0).getUri().toString();
        TemperatureRepresentation temperatureRepresentation = restTemplateImplementation.getTemperatureRepresentation(temperatureUri);

        String humidityUri = discoveryClient.getInstances(humidityName).get(0).getUri().toString();
        HumidityRepresentation humidityRepresentation = restTemplateImplementation.getHumidityRepresentation(humidityUri);
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
            throw new WrongTemperatureUnitException("Temperature unit must be valid (Celsius or Kelvin)!");
        }
    }

}