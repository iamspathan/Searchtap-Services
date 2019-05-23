package me.sohailpathan.www.searchtap;

public class Retailer {


    private String Name;
    private double Pincode;
    private double price;
    private double rating;
    private String ServiceType;


    public Retailer() {

    }

    public Retailer(String name, String serviceType, double pincode, double price, double rating) {
        Name = name;
        Pincode = pincode;
        this.price = price;
        this.rating = rating;
        ServiceType = serviceType;
    }

    public String getName() {
        return Name;
    }

    public double getPincode() {
        return Pincode;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getServiceType() {
        return ServiceType;
    }
}