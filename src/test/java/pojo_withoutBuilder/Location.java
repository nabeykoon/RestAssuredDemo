package pojo_withoutBuilder;

import java.util.List;

public class Location {
    private int id;
    private String city;
    private String country;
    private List<Address> address;

    Location(){}

    public Location(int id, String city, String country, List<Address> address) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
