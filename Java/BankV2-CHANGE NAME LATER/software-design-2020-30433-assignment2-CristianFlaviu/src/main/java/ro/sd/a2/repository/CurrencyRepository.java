package ro.sd.a2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Currency;
import ro.sd.a2.entity.User;

@Repository
public interface CurrencyRepository  extends JpaRepository<Currency, String> {


}