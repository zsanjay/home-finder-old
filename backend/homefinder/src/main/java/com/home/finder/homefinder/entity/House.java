package com.home.finder.homefinder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "houses")
public class House implements Serializable {
    @Id
    private String houseId;
    private int capacity;
    private List<String> imageUrlList;
    private Float price;
    private String description;
    private Category category;
    private User owner;
    private List<LocalDate> reservedDate = new ArrayList<>();
    private City city;
    private String address;

    public House(int capacity, List<String> imageUrlList, Float price, City city, String address) {
        this.capacity = capacity;
        this.imageUrlList = imageUrlList;
        this.price = price;
        this.city = city;
        this.address = address;
    }

    public String getHouseId() {
        return houseId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<LocalDate> getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(List<LocalDate> reservedDate) {
        this.reservedDate = reservedDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
