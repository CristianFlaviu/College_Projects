package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;
import ro.sd.a2.repository.CurrencyRepository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Currency {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private int valuta;

    @Column
    private int comision;

    @Column
    private  int unitValue;

    @OneToMany(mappedBy = "currency")
    private Set<GeneralAccount> accountSet;

    @OneToMany(mappedBy = "currency")
    private Set<Company> companies;

    public Currency()
    {
        this.id="default-id";
        this.name="EURO-default";
        this.valuta=5;
        this.comision=10;
        this.accountSet=new HashSet<>();
        this.companies=new HashSet<>();
    }

    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
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

    public int getValuta() {
        return valuta;
    }

    public void setValuta(int valuta) {
        this.valuta = valuta;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public Set<GeneralAccount> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<GeneralAccount> accountSet) {
        this.accountSet = accountSet;
    }
}
