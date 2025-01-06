package com.solvd.hospital.basicInformation;

public class Address {

    private String street;
    private int number;
    private String city;
    private String postcode;

    public Address(String street, int number, String city, String postcode) throws AddressException {
        if (number <= 0) {
            throw new AddressException("Number must be greater than zero.");
        }
        if (postcode == null || postcode.isEmpty()) {
            throw new AddressException("Postcode cannot be null or empty.");
        }
        this.street = street;
        this.number = number;
        this.city = city;
        this.postcode = postcode;
    }

    @Override
    public final String toString() { // final - always displayed in the same way with toString for better visual
        return street + " " + number + ", " + postcode + " " + city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws AddressException {
        if (number <= 0) {
            throw new AddressException("Number must be greater than zero.");
        }
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) throws AddressException {
        if (postcode == null || postcode.isEmpty()) {
            throw new AddressException("Postcode cannot be null or empty.");
        }
        this.postcode = postcode;
    }
}
