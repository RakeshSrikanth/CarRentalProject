package com.rockooapps.carrentals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rakesh on 7/25/2016.
 */
public class CarElements {

    private String name, makers, type, price, path, model;


    public CarElements(String name, String makers, String type, String price, String path, String model) {
        this.name = name;
        this.makers = makers;
        this.type = type;
        this.price = price;
        this.path = path;
        this.model = model;
    }

    public CarElements() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakers() {
        return makers;
    }

    public void setMakers(String makers) {
        this.makers = makers;
    }

    public String getType() {
        return type;
    }

    public void setType(String image) {
        this.type = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Override
    public String toString() {
        return "CarName: " + name + "   " +
                "\nPrice/hr: " + price+"Rs"+"\nCarModel: "+model+ "\nCarmakers: " + makers + "   " +
                "\nType: " + type;
    }
}