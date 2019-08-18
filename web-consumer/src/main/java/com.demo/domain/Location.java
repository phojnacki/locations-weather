package com.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(nullable = false)
    @Getter @Setter private String requestId;

    @Column(nullable = false)
    @Getter @Setter private String cityName;

    @Column(nullable = false)
    @Getter @Setter private String countryCode;

    @Column(nullable = false)
    @Getter @Setter private String averageTemperatur;

    @Column(nullable = false)
    @Getter @Setter private String averageHumidity;

    @Column(nullable = false)
    @Getter @Setter private String averagePressure;

    public Location() { }

}
