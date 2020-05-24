package service;

import entity.House;
import entity.User;
import repository.UserRepo;

import java.util.Set;

public class UserService {

    private UserRepo userRepo=new UserRepo();


    public void  insertNewUser(User user)
    {
        userRepo.insertNewUser(user);
    }

    public User getUser(String user_id)
    {
        return userRepo.findById(user_id);
    }
    public Set<User> getAllUser()
    {
        return userRepo.findAllUser();
    }

    public Set<House> getAllHouses(String id)
    {
        return userRepo.findAllHousesbyUserId(id);
    }

    public User getUSerByEmail(String email)
    {
        return userRepo.findUserbEmail(email);
    }

}
