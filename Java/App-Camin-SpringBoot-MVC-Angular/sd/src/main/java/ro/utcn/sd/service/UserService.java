package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.repository.UserRepo;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public void add(User user)
    {


        //String pass= BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        //user.setPassword(pass);

        userRepo.save(user);
    }
    public User getById(String id)
    {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAll()
    {
        return userRepo.findAll();
    }

    public void update(User user)
    {
        userRepo.save(user);
    }

    public void deleteById(String id)
    {
        userRepo.deleteById(id);
    }

    public List<User>  getByRoomNumber(String id)
    {
        return userRepo.findAll().stream().filter(a->a.getRoom().getRoomNumber().equals(id)).collect(Collectors.toList());

    }

    public User getByEmail(String email)
    {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepo.findByUsername(username);
        if(Objects.isNull(user))
            throw new UsernameNotFoundException("not found user");

        return user;
    }


    public void updateToken(User user,String token)
    {
        user.setResetPassToken(token);
        userRepo.save(user);
    }


}
