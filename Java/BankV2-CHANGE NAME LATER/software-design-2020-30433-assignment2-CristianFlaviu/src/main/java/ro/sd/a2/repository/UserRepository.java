package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Address;
import ro.sd.a2.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public List<User> findAll();
    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByAddress(Address address);

    public User findByPassword(String string);

    public User findByResetPassCode(String code);

}
