package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Address;
import ro.sd.a2.entity.User;

@Repository
public interface AddressRepository  extends JpaRepository<Address,String> {
}
