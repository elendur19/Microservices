package hr.rasus.microservice.humidity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "measurement")
public class Humidity {

    public Humidity() {
    }

    public Humidity(double humidity) {
        this.humidity = humidity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private double humidity;

}
