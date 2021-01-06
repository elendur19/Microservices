package hr.rasus.microservice.aggregator.ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ WrongTemperatureUnitException.class })
    public ResponseEntity<Object> handleWrongTemperatureUnitException(WrongTemperatureUnitException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);

        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
