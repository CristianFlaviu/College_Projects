package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;
import ro.sd.a2.util.GenerateFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class GeneralAccount {

    public static String SavingAccout="savingAcc";
    public static String SpendingAccount="spendingAcc";

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private  int id;

    @Column
    private String IBAN;

    @Column
    private Date creatinDate;

    @Column
    private  Date lastModificationDate;

    @ManyToOne
    @JoinColumn(name="currency_id")
    private  Currency currency;

    @OneToOne(mappedBy = "generalAccount")
    private Tranzaction tranzaction;

    @Column
    private int sumaBani;

    @Column
    private String type;


    @ManyToOne
    @JoinColumn(name="user_id")
    private  User user;



    public GeneralAccount()
    {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Romania"));
        this.creatinDate=cal.getTime();
        this.currency=new Currency();
        this.lastModificationDate=cal.getTime();
        this.sumaBani=0;
        this.type="default-type";


    }

    public Tranzaction getTranzaction() {
        return tranzaction;
    }

    public void setTranzaction(Tranzaction tranzaction) {
        this.tranzaction = tranzaction;
    }

    public abstract void saveMoney(int suma, int month);
    public abstract int spendMoney(int suma);
    public abstract void setIBan();

    public static String getSavingAccout() {
        return SavingAccout;
    }

    public static void setSavingAccout(String savingAccout) {
        SavingAccout = savingAccout;
    }

    public static String getSpendingAccount() {
        return SpendingAccount;
    }

    public static void setSpendingAccount(String spendingAccount) {
        SpendingAccount = spendingAccount;
    }

    public Date getCreatinDate() {
        return creatinDate;
    }

    public void setCreatinDate(Date creatinDate) {
        this.creatinDate = creatinDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }



    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getSumaBani() {
        return sumaBani;
    }

    public void setSumaBani(int sumaBani) {
        this.sumaBani = sumaBani;
    }
}
