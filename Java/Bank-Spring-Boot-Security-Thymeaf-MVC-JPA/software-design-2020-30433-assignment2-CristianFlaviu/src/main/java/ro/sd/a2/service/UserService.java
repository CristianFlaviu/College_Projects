package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Address;
import ro.sd.a2.entity.User;
import ro.sd.a2.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   public User getById(String id){
       return userRepository.findById(id).orElse(null);
    }

    public void insertUser(User user)
    {
        userRepository.save(user);
    }

    public void deleteUserById(String id)
    {
        userRepository.deleteById(id);
    }
    public User getUserById(String id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }
    public User getByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User getByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public User getByAdress(Address address)
    {
        return userRepository.findByAddress(address);
    }

    public User getByPassword(String password )
    {
        return  userRepository.findByPassword(password);
    }

    public User getByResetPassCode(String code)
    {
        return userRepository.findByResetPassCode(code);
    }
}
