package ro.utcn.sd.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserExampleDto {

    private String name;
    private String id;


    private String errorMessage;

    public UserExampleDto(String name, String id, List<String> phones) {
        this.name = name;
        this.id = id;

    }




}
