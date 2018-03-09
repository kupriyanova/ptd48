package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String name;
    @Expose
    @Column(name = "middlename")
    private String midlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Transient
    private String group;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    //phones
    @Transient
    private String allPhones;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    //emails
    @Transient
    private String allEmail;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withMidlename(String midlename) {
        this.midlename = midlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    // phones
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    // email
    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }
    public ContactData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    //---- geters ----//
    public int getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    //phones
    public String getAllPhones() {
        return allPhones;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }

    //email
    public String getAllEmail() {
        return allEmail;
    }
    public String getFirstEmail() {
        return firstEmail;
    }
    public String getSecondEmail() {
        return secondEmail;
    }
    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getGroup() {
        return group;
    }
    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(midlename, that.midlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(group, that.group) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(firstEmail, that.firstEmail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, midlename, lastname, group, address, homePhone, mobilePhone, workPhone, firstEmail);
    }
}
