package ro.sd.a2.dto;

import ro.sd.a2.entity.GeneralAccount;

public class BriefAccDto {

    private String IBAN;

    private String valuta;

    public BriefAccDto(GeneralAccount generalAccount)
    {
        this.IBAN=generalAccount.getIBAN();
        this.valuta=generalAccount.getCurrency().getName();
    }
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }
}
