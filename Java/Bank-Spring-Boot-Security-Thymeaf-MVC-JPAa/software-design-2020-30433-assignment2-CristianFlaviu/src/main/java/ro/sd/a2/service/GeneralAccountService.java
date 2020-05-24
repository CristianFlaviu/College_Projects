package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Currency;
import ro.sd.a2.entity.GeneralAccount;
import ro.sd.a2.entity.User;
import ro.sd.a2.repository.GeneralAccountRepository;

import java.util.List;

@Service
public class GeneralAccountService {

    @Autowired
    private GeneralAccountRepository generalAccountRepository;


    public void insertGeneralAccount(GeneralAccount generalAccount)
    {
        generalAccountRepository.save(generalAccount);
    }
    public void deleteGeneralAcountById(Integer id)
    {
        generalAccountRepository.deleteById(id);
    }
    public GeneralAccount getGeneralAccountById(Integer id)
    {
        return generalAccountRepository.findById(id).orElse(null);
    }

    public List<GeneralAccount> getAll()
    {
        return generalAccountRepository.findAll();
    }

    public List<GeneralAccount> getAllBySumaLessThan(int suma){
        return generalAccountRepository.findAllBySumaBaniLessThan(suma);
    }
    public List<GeneralAccount> getAllByUser(User user)
    {
        return generalAccountRepository.findAllByUser(user);
    }
    public List<GeneralAccount> getAllByUserTypeCurrency(User user, String type, Currency currency)
    {
        return generalAccountRepository.findAllByUserAndTypeAndCurrency(user,type,currency);
    }

}
