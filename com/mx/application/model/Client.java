package com.mx.application.model;

/*
	Client is the class that have the model of the data that it is going to be extracted from the file and put on a database

	@author Anette Larios
	@since 2023-06-06
*/
public class Client {
    private String name;
    private String phone;
    private String address;
    private String email;
    private String country;
    private Integer numberRange;
    private float balance;
    private String rfc;
    private float tax;
    //Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberRange() {
        return numberRange;
    }

    public void setNumberRange(Integer numberRange) {
        this.numberRange = numberRange;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getRfc() {
        return rfc;
    }

    public void setId(String rfc) {
        this.rfc = rfc;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTax() {
        return tax;
    }
    /*
     * Client is the constructor of Client model
     * @param name, phone, address, email, country, numberRange, balance, rfc, and tax are extracted from the file,
     * processed and send to here by the fileProcessor function
     *
     * @author Anette Larios
     * @since 2023-06-06
     */
    public Client(String name, String phone, String address, String email, String country, Integer numberRange, float balance, String rfc, Float tax ) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.country = country;
        this.numberRange = numberRange;
        this.balance = balance;
        this.rfc = rfc;
        this.tax = tax;
    }
}
