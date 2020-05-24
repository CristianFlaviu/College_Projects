package ro.sd.a2.entity;

public class AccountFactory {

    public static GeneralAccount makeAccount(String type)
    {
        if(type.equals("spending"))
        {
            return new SpendingAccount();
        }
        if(type.equals("saving"))
        {
            return new SavingAccount();
        }
        return null;

    }
}
