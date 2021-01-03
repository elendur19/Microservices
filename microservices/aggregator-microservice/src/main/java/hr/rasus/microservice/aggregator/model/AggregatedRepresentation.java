package hr.rasus.microservice.aggregator.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AggregatedRepresentation {
    private double temperature;
    private double humidity;
}
