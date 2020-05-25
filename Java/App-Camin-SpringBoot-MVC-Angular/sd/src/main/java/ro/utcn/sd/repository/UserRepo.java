package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {


     User findByEmail(String email);
     User findByUsername(String username);}
