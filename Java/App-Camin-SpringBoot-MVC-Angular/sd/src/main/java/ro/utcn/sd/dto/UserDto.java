package ro.utcn.sd.dto;

import lombok.Data;

import ro.utcn.sd.entity.User;

@Data
public class UserDto {


    private String id;
    private String roomNumber;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;







}
