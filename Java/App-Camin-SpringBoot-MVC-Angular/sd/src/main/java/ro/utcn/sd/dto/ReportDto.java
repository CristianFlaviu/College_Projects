package ro.utcn.sd.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {




    private String id;
    private String roomNumber;
    private Date date;
    private String title;
    private String description;

}
