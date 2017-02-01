package com.bassem.roombooking.models;

/**
 * Created by Bassem Samy on 1/31/2017.
 */

public class Room {
    private String location;

    private String name;

    private String[] images;

    private String capacity;

    private String[] avail;

    private String[] equipment;

    private String size;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String[] getAvail() {
        return avail;
    }

    public void setAvail(String[] avail) {
        this.avail = avail;
    }

    public String[] getEquipment() {
        return equipment;
    }

    public void setEquipment(String[] equipment) {
        this.equipment = equipment;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClassPojo [location = " + location + ", name = " + name + ", images = " + images + ", capacity = " + capacity + ", avail = " + avail + ", equipment = " + equipment + ", size = " + size + "]";
    }
}
