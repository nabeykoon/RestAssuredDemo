package pojo;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String flat_no;
    private int pinCode;
    private String type;
}
