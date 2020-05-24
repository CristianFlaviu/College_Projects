package ro.sd.a2.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Tranzaction {

    @Id
    private String id;

    @Column
    private Date date;


    @OneToOne
    @JoinColumn(name="factura_id")
    private Factura factura;

    @OneToOne
    @JoinColumn(name = "account_id")
    private GeneralAccount generalAccount;

    public GeneralAccount getGeneralAccount() {
        return generalAccount;
    }

    public void setGeneralAccount(GeneralAccount generalAccount) {
        this.generalAccount = generalAccount;
    }

    public Tranzaction()
    {
        this.id= UUID.randomUUID().toString();
        this.date=new Date();
    }

    @Override
    public String toString() {
        return "Tranzaction  " +id+" "+date +" ";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
