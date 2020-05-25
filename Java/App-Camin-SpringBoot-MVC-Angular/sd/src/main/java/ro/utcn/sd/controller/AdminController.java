package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.Exceptions.DuplicateDataException;
import ro.utcn.sd.Exceptions.MissingAtributeException;
import ro.utcn.sd.Exceptions.WrongFormatException;
import ro.utcn.sd.constants.EndpointsConstant;
import ro.utcn.sd.constants.ClientMessages;
import ro.utcn.sd.dto.RoomDto;
import ro.utcn.sd.dto.UserDto;
import ro.utcn.sd.entity.Room;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.mapper.RoomMapper;
import ro.utcn.sd.mapper.UserMapper;
import ro.utcn.sd.service.RoomService;
import ro.utcn.sd.service.UserService;
import ro.utcn.sd.util.ApiResponseFactory;
import ro.utcn.sd.validator.RoomValidator;


import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")

public class AdminController {

    private final static Logger LOGGER = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;


    @GetMapping(value = EndpointsConstant.VIEW_USER + "{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUserById(@PathVariable String id) {
        User user = userService.getById(id);
        String message;

        if (Objects.isNull(user)) {

            message = String.format(ClientMessages.OBJECT_NOT_EXISTS, "user", id);
            return ApiResponseFactory.createErrorMessage(null, message, HttpStatus.BAD_REQUEST);
        }

        UserDto userDto = UserMapper.UserToDto(user);

        message = ClientMessages.SUCCESS;
        return ApiResponseFactory.createSucessMessage(userDto, message);

    }




    @GetMapping(value = EndpointsConstant.FIND_BY_ROOM_NUMBER + "{roomNumber}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity findUserByEmail(@PathVariable String roomNumber) {

        List<UserDto> allUsers = userService.getByRoomNumber(roomNumber).stream().map(UserMapper::UserToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(allUsers, ClientMessages.SUCCESS);

    }

    @GetMapping(value = EndpointsConstant.ALL_USERS, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getAllUsers() {

        List<UserDto> allUsers = userService.getAll().stream().map(UserMapper::UserToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(allUsers, ClientMessages.SUCCESS);

    }

    @GetMapping(EndpointsConstant.ALL_USERS_BY_ROOMNUMBER+"{number}")
    public ResponseEntity getAllUserByRoomNumber(@PathVariable String number)
    {
        List<UserDto> userDtoList=userService.getAll().stream().filter(a->a.getRoom().getRoomNumber().equals(number)).map(UserMapper::UserToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(userDtoList,"success");

    }

    @DeleteMapping(value = EndpointsConstant.DELETE_USER + "{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteUser(@PathVariable String id) {
        User user = userService.getById(id);
        String clientMessage;

        if (Objects.isNull(user)) {
            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "user", id);
            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);

        }

        if (user.getAnnouncements().size() > 0) {
            return ApiResponseFactory.createErrorMessage(null, String.format(ClientMessages.NOT_ALLOWED_DELETE, "user"), HttpStatus.BAD_REQUEST);
        }

        userService.deleteById(id);
        List<UserDto> allUsers = userService.getAll().stream().map(UserMapper::UserToDto).collect(Collectors.toList());

        clientMessage = String.format(ClientMessages.SUCCEFUL_DELETE, "user", id);
        return ApiResponseFactory.createSucessMessage(allUsers, clientMessage);
    }

    /**
     * Rooms Operations
     */
    @GetMapping(EndpointsConstant.ALL_ROOMS)
    public ResponseEntity getAllRooms() {
        List<RoomDto> roomList = roomService.getAllRooms().stream().map(RoomMapper::RoomToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(roomList, ClientMessages.SUCCESS);
    }

    @PostMapping(EndpointsConstant.ADD_ROOM)
    public ResponseEntity addRoom(@RequestBody RoomDto roomDto) {

        String id = UUID.randomUUID().toString();
        Room room = RoomMapper.DtoToRoom(roomDto, id);
        String clientMessage;

        try {
            RoomValidator.Validate(room,roomService.getAllRooms());
            roomService.add(room);

            clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "room", room.getId());

            return ApiResponseFactory.createSucessMessage(roomService.getAllRooms(), clientMessage);


        } catch (NullPointerException e1) {
            return ApiResponseFactory.createErrorMessage(null, "internal error message", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (
                MissingAtributeException e2) {
            return ApiResponseFactory.createErrorMessage(null, e2.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (DuplicateDataException e3)
        {
            return ApiResponseFactory.createErrorMessage(null,e3.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (WrongFormatException e4)
        {
            return ApiResponseFactory.createErrorMessage(null,e4.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(EndpointsConstant.DELETE_ROOM + "{id}")
    public ResponseEntity deleteRoom(@PathVariable String id) {

        Room room = roomService.getById(id);
        String clientMessage;

        if (Objects.isNull(room)) {
            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "room", id);
            LOGGER.warning("room with with id "+id + "does not exist");

            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        } else if (room.getUsers().size() > 0 || room.getReports().size() > 0 || room.getAppointments().size() > 0) {
            LOGGER.warning("room can not be deleted");
            return ApiResponseFactory.createErrorMessage(null, String.format(ClientMessages.NOT_ALLOWED_DELETE, "room"), HttpStatus.NOT_ACCEPTABLE);

        } else {
            roomService.deleteRoomById(id);

            clientMessage ="room with Number "+room.getRoomNumber()+" has been deleted ";
            List<RoomDto> listRooms=roomService.getAllRooms().stream().map(RoomMapper::RoomToDto).collect(Collectors.toList());
            return ApiResponseFactory.createSucessMessage(listRooms, clientMessage);
        }
    }


}
