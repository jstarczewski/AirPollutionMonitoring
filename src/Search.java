package com.company;

import java.util.ArrayList;

public class Search {

    public String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static boolean isNameValid(ArrayList city) {
        return city.size() >= 10;
    }

    public static boolean getSensorList() {
        return false;
    }
}
