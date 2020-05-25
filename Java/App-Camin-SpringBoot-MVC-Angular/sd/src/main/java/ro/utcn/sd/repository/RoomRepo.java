package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room,String> {

    public Room findByRoomNumber(String number);
}
