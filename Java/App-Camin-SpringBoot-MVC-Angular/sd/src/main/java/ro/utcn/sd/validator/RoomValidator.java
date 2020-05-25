package ro.utcn.sd.validator;

import ro.utcn.sd.Exceptions.DuplicateDataException;
import ro.utcn.sd.Exceptions.MissingAtributeException;
import ro.utcn.sd.Exceptions.WrongFormatException;
import ro.utcn.sd.entity.Room;

import java.util.List;
import java.util.Objects;

public class RoomValidator {

    public static void Validate(Room room, List<Room> roomList)
    {
        if(Objects.isNull(room))
            throw new NullPointerException();
        if(Objects.isNull(room.getRoomNumber())|| room.getRoomNumber().equals(""))
            throw new MissingAtributeException("Field RoomNumber is epty");
        if(roomList.stream().anyMatch(a->a.getRoomNumber().equals(room.getRoomNumber())))
        {
            throw  new DuplicateDataException("This roomNumber already exixts");
        }

        try {
            int nr=Integer.valueOf(room.getRoomNumber());
        }catch (Exception e)
        {
            throw new WrongFormatException("Unacceptable Room Number Format");
        }




    }
}
