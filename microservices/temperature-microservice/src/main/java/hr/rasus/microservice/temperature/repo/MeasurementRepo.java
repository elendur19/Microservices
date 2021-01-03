package hr.rasus.microservice.temperature.repo;

import hr.rasus.microservice.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeasurementRepo extends JpaRepository<Temperature, Long> {
    @Query("SELECT m FROM Temperature m WHERE m.Id = :id")
    Temperature findById(@Param("id") long id);
}
