package hr.rasus.microservice.aggregator.controller;

import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.service.AggregatorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AggregatorController {

    private final AggregatorServiceImpl aggregatorService;

    @GetMapping("/readings")
    public AggregatedRepresentation getAggregatedMeasurements() {
        return aggregatorService.getAggregatedMeasurements();
    }


}
