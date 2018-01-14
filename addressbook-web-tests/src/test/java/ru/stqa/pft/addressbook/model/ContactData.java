package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String midlename;
    private final String lastname;
    private final String mobile;
    private final String email;

    public ContactData(String name, String midlename, String lastname, String mobile, String email) {
        this.name = name;
        this.midlename = midlename;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMidlename() {
        return midlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}
