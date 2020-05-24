package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Currency;
import ro.sd.a2.entity.GeneralAccount;
import ro.sd.a2.entity.User;

import java.util.List;

@Repository
public interface GeneralAccountRepository  extends JpaRepository<GeneralAccount, Integer> {

    public List<GeneralAccount> findAllBySumaBaniLessThan(int number);
    public List<GeneralAccount> findAllByUser(User user);

    public List<GeneralAccount> findAllByUserAndTypeAndCurrency(User user, String type, Currency currency);
}
