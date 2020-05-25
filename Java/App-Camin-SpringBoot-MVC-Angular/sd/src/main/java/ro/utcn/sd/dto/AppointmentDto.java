package ro.utcn.sd.dto;

import lombok.Data;
import ro.utcn.sd.entity.Appointment;
import ro.utcn.sd.entity.Room;

@Data
public class AppointmentDto {

    private String message;

    private String id;
    private String startHour;
    private String day;
    private String roomNumber;
    private String type;



    public AppointmentDto(Appointment appointment)
    {
        this.startHour=appointment.getStartHour();
        this.day=appointment.getDay();
        this.roomNumber=appointment.getRoom().getRoomNumber();
        this.id=appointment.getId();
        this.type=appointment.getType();
    }

    public AppointmentDto()
    {

    }


}
