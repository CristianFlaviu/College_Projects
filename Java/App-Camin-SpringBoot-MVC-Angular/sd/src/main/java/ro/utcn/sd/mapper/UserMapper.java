package ro.utcn.sd.mapper;

import ro.utcn.sd.dto.UserDto;
import ro.utcn.sd.entity.Room;
import ro.utcn.sd.entity.User;

public class UserMapper {


    public static UserDto UserToDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setRoomNumber(user.getRoom().getRoomNumber());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());


        return userDto;
    }

    public static User DtoToUser(UserDto userDto, String id, Room room)
    {
        User user=new User();

        user.setId(id);
        user.setRoom(room);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
