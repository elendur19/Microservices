package hr.rasus.microservice.temperature.controller;

import hr.rasus.microservice.temperature.service.TemperatureService;
import hr.rasus.microservice.temperature.model.TemperatureRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;

    @GetMapping("/current-reading")
    public TemperatureRepresentation getTemperature() {
        return temperatureService.getTemperature();
    }
}
