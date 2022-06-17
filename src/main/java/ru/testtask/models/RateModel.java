package ru.testtask.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RateModel {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;

}
