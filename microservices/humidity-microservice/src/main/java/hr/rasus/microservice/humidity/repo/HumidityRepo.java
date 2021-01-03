package hr.rasus.microservice.humidity.repo;

import hr.rasus.microservice.humidity.entity.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HumidityRepo extends JpaRepository<Humidity, Long> {
    @Query("SELECT m FROM Humidity m WHERE m.Id = :id")
    Humidity findById(@Param("id") long id);
}
