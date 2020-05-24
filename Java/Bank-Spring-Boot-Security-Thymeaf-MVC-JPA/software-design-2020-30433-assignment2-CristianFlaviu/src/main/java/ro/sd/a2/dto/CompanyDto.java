package ro.sd.a2.dto;

import ro.sd.a2.entity.Company;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyDto {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    private String currency_id;


}
