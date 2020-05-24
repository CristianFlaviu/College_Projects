package ro.sd.a2.dto;

import ro.sd.a2.entity.Currency;

public class CurrencyDto {

    private String id;
    private String name;

    public CurrencyDto(Currency currency)
    {
        this.id=currency.getId();
        this.name=currency.getName();

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
}
