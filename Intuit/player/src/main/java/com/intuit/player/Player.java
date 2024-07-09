package com.intuit.player;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Player {
    @CsvBindByName()
    private String playerID;
    @CsvBindByName()
    private Integer birthYear;
    @CsvBindByName()
    private Integer birthMonth;
    @CsvBindByName()
    private Integer birthDay;
    @CsvBindByName()
    private String birthCountry;
    @CsvBindByName()
    private String birthState;
    @CsvBindByName()
    private String birthCity;
    @CsvBindByName()
    private Integer deathYear;
    @CsvBindByName()
    private Integer deathMonth;
    @CsvBindByName()
    private Integer deathDay;
    @CsvBindByName()
    private String deathCountry;
    @CsvBindByName()
    private String deathState;
    @CsvBindByName()
    private String deathCity;
    @CsvBindByName()
    private String nameFirst;
    @CsvBindByName()
    private String nameLast;
    @CsvBindByName()
    private String nameGiven;
    @CsvBindByName()
    private Integer weight;
    @CsvBindByName()
    private Integer height;
    @CsvBindByName()
    private String bats;
    @CsvBindByName(column = "throws")
    @JsonProperty("throws")
    private String throws_; // throws is a saved keyword in java
    @CsvBindByName()
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate debut;
    @CsvBindByName()
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate finalGame;
    @CsvBindByName()
    private String retroID;
    @CsvBindByName()
    private String bbrefID;
}
