package com.springapp.mvc.data.utils;

import java.io.Serializable;
import java.util.List;

public class ActivitiesRequest implements Serializable {
    private float latitude;
    private float longitude;
    private int maxTravelDistance;
    private List<Long> checkedCategories;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(int maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public List<Long> getCheckedCategories() {
        return this.checkedCategories;
    }

    public void setCheckedCategories(List<Long> checkedCategories) {
        this.checkedCategories = checkedCategories;
    }
}
