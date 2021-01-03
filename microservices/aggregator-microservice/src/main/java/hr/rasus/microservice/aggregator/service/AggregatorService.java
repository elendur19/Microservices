package hr.rasus.microservice.aggregator.service;

import hr.rasus.microservice.aggregator.RestTemplateImplementation;
import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.model.HumidityRepresentation;
import hr.rasus.microservice.aggregator.model.TemperatureRepresentation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AggregatorService {

    private final RestTemplateImplementation restTemplateImplementation;

    public AggregatorService(RestTemplateImplementation restTemplateImplementation) {
        this.restTemplateImplementation = restTemplateImplementation;
        temperatureUrl = null;
        humidityUrl = null;
    }
    @Value("${temperature-url}")
    private final String temperatureUrl;
    @Value("${humidity-url}")
    private final String humidityUrl;

    public AggregatedRepresentation getAggregatedMeasurements() {
        AggregatedRepresentation aggregatedRepresentation = new AggregatedRepresentation();
        TemperatureRepresentation temperatureRepresentation = restTemplateImplementation.getTemperatureRepresentation(temperatureUrl);

        HumidityRepresentation humidityRepresentation = restTemplateImplementation.getHumidityRepresentation(humidityUrl);

        aggregatedRepresentation.setTemperature(temperatureRepresentation.getTemperature());
        aggregatedRepresentation.setHumidity(humidityRepresentation.getHumidity());

        return aggregatedRepresentation;
    }
}
