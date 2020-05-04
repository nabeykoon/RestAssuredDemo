package pojo_withoutBuilder;

public class Address {
    private String street;
    private String flat_no;
    private int pinCode;
    private String type;

    Address(){}

    public Address(String street, String flat_no, int pinCode, String type) {
        this.street = street;
        this.flat_no = flat_no;
        this.pinCode = pinCode;
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlat_no() {
        return flat_no;
    }

    public void setFlat_no(String flat_no) {
        this.flat_no = flat_no;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
