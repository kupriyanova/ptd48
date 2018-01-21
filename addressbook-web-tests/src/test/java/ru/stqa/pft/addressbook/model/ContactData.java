package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String midlename;
    private final String lastname;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(String name, String midlename, String lastname, String mobile, String email, String group) {
        this.name = name;
        this.midlename = midlename;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}
