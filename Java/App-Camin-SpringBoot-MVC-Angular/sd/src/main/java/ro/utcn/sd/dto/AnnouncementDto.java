package ro.utcn.sd.dto;

import lombok.Data;
import ro.utcn.sd.entity.User;

import java.util.Date;

@Data
public class AnnouncementDto {


    private String id;
    private String userName;
    private Date date;
    private String title;
    private String description;

}
