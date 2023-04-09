package Dto;

import Entities.Role;

public class UserData {

    private int userId;
    private String password;
    private String userNameSurname;
    private String email;
    private String addressStreet;
    private String addressBuildingNumber;
    private String apartmentNumber;
    private String city;
    private String country;
    private String zipCode;
    private double balance;
    private Role userRole;

    public UserData() {
    }

    public UserData(int userId, String password, String userNameSurname, String email, String addressStreet,
                    String addressBuildingNumber, String apartmentNumber, String city, String country, String zipCode, double balance, Role userRole) {
        this.userId = userId;
        this.password = password;
        this.userNameSurname = userNameSurname;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressBuildingNumber = addressBuildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.balance = balance;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNameSurname() {
        return userNameSurname;
    }

    public void setUserNameSurname(String userNameSurname) {
        this.userNameSurname = userNameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressBuildingNumber() {
        return addressBuildingNumber;
    }

    public void setAddressBuildingNumber(String addressBuildingNumber) {
        this.addressBuildingNumber = addressBuildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", userNameSurname='" + userNameSurname + '\'' +
                ", email='" + email + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressBuildingNumber='" + addressBuildingNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", balance=" + balance +
                ", userRole=" + userRole +
                '}';
    }
}