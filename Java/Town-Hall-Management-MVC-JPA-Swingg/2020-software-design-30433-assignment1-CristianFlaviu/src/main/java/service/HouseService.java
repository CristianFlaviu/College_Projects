package service;

import entity.House;
import exception.DuplicateKeyException;
import repository.HouseRepo;
import validators.HouseValidator;

import java.util.List;
import java.util.Set;

public class HouseService {
    private HouseRepo houseRepo=new HouseRepo();

    public void insertNewHouse(House house)
    {
        HouseValidator.HouseValidator(house);

        if(houseRepo.findById(house.getId())==null)
        {
            houseRepo.insertNewHouse(house);
        }
        else
        {
           throw new DuplicateKeyException("");
        }


    }
    public  void removeHouse(String house_id)
    {
        houseRepo.removeHouse(house_id);
    }

    public Set<House> getAllHouses()
    {
        return houseRepo.findAllHouses();
    }

    public  Set<House> getAllHouseByUserId(String id)
    {
        return houseRepo.findAllHouseByUserId(id);
    }
}
