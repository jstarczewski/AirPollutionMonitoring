package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.company.Search.isNameValid;

public class Main {

    public static void main(String[] args) {

        ArrayList city = new ArrayList();
        city.add("Warsaw");

        if (isNameValid(city)) {
            System.out.println("true");
        }
        if (!isNameValid(city)){
            System.out.println("false");
        }
    }
}
