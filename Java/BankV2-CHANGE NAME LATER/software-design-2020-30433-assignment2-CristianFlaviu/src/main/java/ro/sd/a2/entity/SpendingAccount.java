package ro.sd.a2.entity;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@Entity
public class SpendingAccount extends GeneralAccount {

    public SpendingAccount()
    {

        this.setType(GeneralAccount.SpendingAccount);
    }

    @Override
    public void saveMoney(int suma, int month) {

        setSumaBani(suma+getSumaBani());
    }

    @Override
    public int spendMoney(int suma_unit) {

        int suma=suma_unit/getCurrency().getUnitValue();



        setSumaBani(getSumaBani()-suma-suma*getCurrency().getComision()/100);
        return getSumaBani();
    }

    public void setIBan() {

        Random random=new Random();
        int rand_int=random.nextInt(80000)+10000;
        int rand_int2=random.nextInt(800000)+100000;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Romania"));
        cal.setTime(getCreatinDate());

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int suma=hour+minute;
        String accTypeString;


        User user=getUser();


        String Iban="HNG"+"#"+user.getAddress().getCountry().substring(0,2).toUpperCase()+
                rand_int +
                user.getFirstName().substring(0,2).toUpperCase()+
                user.getLastName().substring(user.getLastName().length()-2).toUpperCase()+
                suma+getCurrency().getName()+rand_int2+user.getAddress().getCity().substring(0,2).toUpperCase();

        setIBAN(Iban);

        setIBAN(Iban);
    }
}
