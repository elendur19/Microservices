package hr.rasus.microservice.aggregator.ex;

public class WrongTemperatureUnitException extends RuntimeException {
    public WrongTemperatureUnitException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
