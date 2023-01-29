package org.example;

public class Address {
    private final String city;
    private final String street;
    private final int house;
    private final int floor;

    public Address(String city, String street, int house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return city + " " + street +
                " " + house + " " + floor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        Address address = (Address) obj;
        return city.equals((address).city) && street.equals(address.street) &&
                house == address.house && floor == address.floor;
    }

    @Override
    public int hashCode() {
        int result = city == null ? 0 : city.hashCode();
        result += street.hashCode() + house + floor;
        return result;
    }
}
