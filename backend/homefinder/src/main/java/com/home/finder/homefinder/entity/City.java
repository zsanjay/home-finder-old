package com.home.finder.homefinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "cities")
public class City implements Serializable {
    @Id
    private String cityId;
    private String cityName;
    private String cityImageUrl;
    public City(String cityName) {
        this.cityName = cityName;
    }
    public String getCityId() {
        return cityId;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityImageUrl() {
        return cityImageUrl;
    }

    public void setCityImageUrl(String cityImageUrl) {
        this.cityImageUrl = cityImageUrl;
    }
}
