package ro.sd.a2.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailDTO {

    @NotNull
    @Size(min=2, max=30)
    private String subject;

    @Size(min=10, max=50)
    @NotNull
    private  String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
