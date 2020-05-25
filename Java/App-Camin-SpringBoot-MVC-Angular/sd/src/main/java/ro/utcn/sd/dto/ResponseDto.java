package ro.utcn.sd.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private String message;
    private String severity;
    private Object data;



}
