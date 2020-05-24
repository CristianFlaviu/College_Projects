package ro.sd.a2.entity;

import javax.persistence.*;

@Entity
public class Factura {

    @Id
    private String id;

    @Column
    private int sumaBani;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToOne(mappedBy = "factura")
    private Tranzaction tranzaction;



    public Factura()
    {
        this.id="default-id";
        this.sumaBani=50;
    }

    public int getSumaBani() {
        return sumaBani;
    }

    public void setSumaBani(int sumaBani) {
        this.sumaBani = sumaBani;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
