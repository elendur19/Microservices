package hr.rasus.microservice.humidity.controller;

import hr.rasus.microservice.humidity.model.HumidityRepresentation;
import hr.rasus.microservice.humidity.service.HumidityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HumidityController {

    private final HumidityService humidityService;

    @GetMapping("/current-reading")
    public HumidityRepresentation getHumidity() {
        return humidityService.getHumidity();
    }

}
