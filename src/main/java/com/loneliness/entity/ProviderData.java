package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

public class ProviderData implements Serializable {
    private static final long serialVersionUID=2L;
    private int id;
    @NotNull (message = "Поставшик должен иметь имя ")private String name;
    @Min(value = -1, message = "Минимальный рейтинг 0 ")
    @Max(value = 10,message = "Максимальный рейтинг 10 ")
    private int rating;
    @NotNull (message = "Поставшик должен указать местоположение ")
    private String location;
    @NotNull(message = "Введите эл.почту ")
    @Email(message = "Почта введена неверно ")
    private String email;
    private String balance;
    private transient StringProperty locationString;
    private transient IntegerProperty ratingInteger;

    public ProviderData() {
    }

    public ProviderData(int id, String name, int rating, String location) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.location = location;
        locationString=new SimpleStringProperty(location);
        ratingInteger=new SimpleIntegerProperty(rating);
    }

    public ProviderData(int id, String name, int rating, String location, StringProperty locationString, IntegerProperty ratingInteger) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.locationString = locationString;
        this.ratingInteger = ratingInteger;
    }

    public int getRatingInteger() {
        return ratingInteger.get();
    }

    public IntegerProperty ratingIntegerProperty() {
        if(ratingInteger==null){
            ratingInteger=new SimpleIntegerProperty(rating);
        }
        return ratingInteger;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setRatingInteger(int ratingInteger) {
        this.ratingInteger.set(ratingInteger);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        ratingInteger=new SimpleIntegerProperty(rating);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        locationString=new SimpleStringProperty(location);
    }

    public String getLocationString() {
        return locationString.get();
    }

    public StringProperty locationStringProperty() {
        if(locationString==null){
            locationString=new SimpleStringProperty(location);
        }
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString.set(locationString);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderData that = (ProviderData) o;
        return id == that.id &&
                rating == that.rating &&
                name.equals(that.name) &&
                location.equals(that.location) &&
                email.equals(that.email) &&
                Objects.equals(locationString, that.locationString) &&
                Objects.equals(ratingInteger, that.ratingInteger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, location, email, locationString, ratingInteger);
    }

    @Override
    public String toString() {
        return "ProviderData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", locationString=" + locationString +
                ", ratingInteger=" + ratingInteger +
                '}';
    }
}
