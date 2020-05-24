package ro.sd.a2.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {

    @Id
    private String id;

    @Column
    @NotNull
    @Size(min=2, max=30)
    private String name;

    @ManyToOne
    @JoinColumn(name="currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "company")
    private Set<Factura> facturaSet;


    public Company()
    {
        this.id="DefaultID";
        this.name="Default-RCS";
        this.facturaSet=new HashSet<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Factura> getCompanySet() {
        return facturaSet;
    }

    public void setCompanySet(Set<Factura> companySet) {
        this.facturaSet = companySet;
    }
}
