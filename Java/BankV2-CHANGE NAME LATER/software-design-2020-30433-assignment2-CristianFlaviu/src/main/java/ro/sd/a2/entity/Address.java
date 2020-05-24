package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    private String id;

    @Column
    private String country;

    @Column
    private  String city;

    @OneToOne(mappedBy = "address")

    private User user;

    public Address()
    {
        this.id="default-id";
        this.country="Romania";
        this.city="Baia Mare";

    }

    @Override
    public String toString() {
        return country+" "+city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
