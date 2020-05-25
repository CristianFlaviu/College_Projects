package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Room;
import ro.utcn.sd.repository.RoomRepo;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    public void add(Room room)
    {
        roomRepo.save(room);
    }

    public Room getById(String id)
    {
        return roomRepo.findById(id).orElse(null);
    }

    public Room getByNumber(String number)
    {
        return roomRepo.findByRoomNumber(number);
    }

    public List<Room> getAllRooms()
    {
        return roomRepo.findAll();
    }

    public void deleteRoomById(String id)
    {
         roomRepo.deleteById(id);
    }
}
