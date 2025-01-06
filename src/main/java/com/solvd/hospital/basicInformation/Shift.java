package com.solvd.hospital.basicInformation;

public enum Shift {

    DAY("Day shift", "6am - 6pm"),
    NIGHT("Night shift", "6pm - 6am");

    final String displayName;
    final String hours;

    Shift(String displayName, String hours) {
        this.displayName = displayName;
        this.hours = hours;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHours() {
        return hours;
    }

    public Shift getNextShift() {
        if (this == DAY) {
            return NIGHT;
        } else {
            return DAY;
        }
    }

    public boolean isTimeInShift(int hour) {
        if (this == DAY) {
            return hour >= 6 && hour < 18;
        } else {
            return (hour >= 18 && hour < 24) || (hour >= 0 && hour < 6);
        }
    }
}