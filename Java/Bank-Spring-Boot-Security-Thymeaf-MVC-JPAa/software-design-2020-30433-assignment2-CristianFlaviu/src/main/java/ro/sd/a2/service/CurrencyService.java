package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Currency;
import ro.sd.a2.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public void insertCurrency(Currency currency)
    {
        currencyRepository.save(currency);
    }
    public void deleteById(String id)
    {
        currencyRepository.deleteById(id);
    }
    public Currency getById(String id)
    {
        return currencyRepository.findById(id).orElse(null);
    }

    public List<Currency> getAll()
    {
        return currencyRepository.findAll();
    }

}
