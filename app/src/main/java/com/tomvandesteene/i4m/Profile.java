package com.tomvandesteene.i4m;

/**
 * Created by Tom Van de Steene on 20/06/2017.
 */

public class Profile {

    private int id;
    private byte[] photo;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String location;
    private String lang1;
    private String lang2;
    private String lang3;
    private String lang4;
    private String skills;
    private String dateOfBirth;

    //empty constructor
    public Profile() {
    }

    //complete constructor for DetailActivity
    public Profile(int id, byte[] photo, String firstName, String lastName, String telephone, String email,
                   String streetNumber, String streetName, String postalCode, String location,
                   String lang1, String lang2, String lang3, String lang4, String skills, String dateOfBirth) {
        this.id = id;
        this.photo = photo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.location = location;
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.lang3 = lang3;
        this.lang4 = lang4;
        this.skills = skills;
        this.dateOfBirth = dateOfBirth;
    }

    //constructor with photo, firstname, lastname, telephone and email for list
    public Profile(byte[] photo, String firstName, String lastName, String telephone, String email) {
        this.photo = photo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLang1() {
        return lang1;
    }

    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    public String getLang2() {
        return lang2;
    }

    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    public String getLang3() {
        return lang3;
    }

    public void setLang3(String lang3) {
        this.lang3 = lang3;
    }

    public String getLang4() {
        return lang4;
    }

    public void setLang4(String lang4) {
        this.lang4 = lang4;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
