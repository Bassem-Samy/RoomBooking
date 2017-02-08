package com.bassem.roombooking.models;

import java.nio.charset.Charset;
import java.util.Calendar;

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

    public boolean isInFilter(FilterObj filter) {
        return checkIsInFilter(filter);

    }

    private boolean checkIsInFilter(FilterObj filter) {
        boolean satisfies = true;
        if (filter.mNameFilter != null && filter.mNameFilter.isEmpty() == false) {
            if (this.name.toLowerCase().contains(filter.mNameFilter.toLowerCase()) == false) {
                return false;
            }
        }
        if (filter.mAvailableFilter == true) {
            if (this.isAvailableNextHour(filter.selectedDay) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean isAvailableNextHour(Calendar selectedDay) {
        if (selectedDay == null) {
            return true;

        }
        selectedDay.add(Calendar.HOUR_OF_DAY, 1);
        int nextHour = selectedDay.get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i < avail.length; i++) {
            String[] splits = avail[i].split("-");
            if (splits.length == 2) {
                String[] startHourMinute = splits[0].trim().split(":");
                int startIndex = Integer.parseInt(startHourMinute[0]);

                String[] endHourMinute = splits[1].trim().split(":");
                int endIndex = (Integer.parseInt(endHourMinute[0]));
                if (nextHour >= startIndex && nextHour <= endIndex) {
                    return true;
                }
            }

        }
        return false;
    }

    public class FilterObj {
        private String mNameFilter;
        private boolean mAvailableFilter;
        Calendar selectedDay;

        public void setFilters(String nameFilter, boolean availableInNextHour, Calendar selectedDay) {
            this.mNameFilter = nameFilter;
            this.mAvailableFilter = availableInNextHour;
            this.selectedDay = selectedDay;
        }
    }
}
