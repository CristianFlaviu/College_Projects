package ro.utcn.sd.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.utcn.sd.constants.Severity;
import ro.utcn.sd.dto.ResponseDto;

public  class  ApiResponseFactory {



//    ERROR_MESSAGE
//    INFORMATION_MESSAGE
//    WARNING_MESSAGE
//    QUESTION_MESSAGE
//    PLAIN_MESSAGE
    public static ResponseEntity createSucessMessage(Object object,String message)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setData(object);
        responseDto.setMessage(message);
        responseDto.setSeverity(Severity.SUCCESS_ALERT);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
    public static ResponseEntity createErrorMessage(Object object,String message,HttpStatus httpStatus)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(object);

        switch (httpStatus)
        {
            case BAD_REQUEST:
                responseDto.setSeverity(Severity.INFO_ALERT);
            case NOT_ACCEPTABLE:
                responseDto.setSeverity(Severity.DANGER_ALERT);
            case CONFLICT:
                responseDto.setSeverity(Severity.DANGER_ALERT);
        }
        responseDto.setData(object);

        return ResponseEntity.status(httpStatus).body(responseDto);

    }


}
