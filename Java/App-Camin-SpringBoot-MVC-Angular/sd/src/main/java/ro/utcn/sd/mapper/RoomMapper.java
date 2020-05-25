package ro.utcn.sd.mapper;

import ro.utcn.sd.dto.RoomDto;
import ro.utcn.sd.entity.Room;

public class RoomMapper {

    public static RoomDto RoomToDto(Room room)
    {
        RoomDto roomDto=new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setNumberStudens(room.getUsers().size());

        return roomDto;
    }

    public static Room DtoToRoom(RoomDto roomDto,String id)
    {
        Room room=new Room();
        room.setId(id);
        room.setRoomNumber(roomDto.getRoomNumber());

        return room;

    }
}
