package hr.rasus.microservice.aggregator.controller;

import hr.rasus.microservice.aggregator.model.AggregatedRepresentation;
import hr.rasus.microservice.aggregator.service.AggregatorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AggregatorController {

    private final AggregatorService aggregatorService;

    @Value("${pozdrav}")
    private String tempUnit;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
        tempUnit = null;
    }

    @GetMapping("/readings")
    public AggregatedRepresentation getAggregatedMeasurements() {
        String a = tempUnit;
        return aggregatorService.getAggregatedMeasurements();
    }


}
