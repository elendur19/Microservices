package hr.rasus.microservice.temperature.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "measurement")
public class Temperature {

    public Temperature() {
    }

    public Temperature(double temperature) {
        this.temperature = temperature;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private double temperature;

}
