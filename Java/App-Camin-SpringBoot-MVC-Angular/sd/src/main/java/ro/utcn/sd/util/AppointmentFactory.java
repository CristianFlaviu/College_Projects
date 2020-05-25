package ro.utcn.sd.util;

import ro.utcn.sd.constants.AppointmentType;
import ro.utcn.sd.dto.AppointmentDto;
import ro.utcn.sd.entity.Appointment;
import ro.utcn.sd.entity.Room;
import ro.utcn.sd.mapper.AppointmentMapper;

import java.util.UUID;

public class AppointmentFactory {

    public static Appointment createWMAppforRoom( AppointmentDto appointmentDto,Room room)
    {
        Appointment appointment= AppointmentMapper.DtoToAppointment(appointmentDto,room);
        appointment.setId(UUID.randomUUID().toString());
        appointment.setType(AppointmentType.Water_Machine);

        return appointment;
    }
    public static Appointment createFootballAppforRoom( AppointmentDto appointmentDto,Room room)
    {
        Appointment appointment= AppointmentMapper.DtoToAppointment(appointmentDto,room);
        appointment.setId(UUID.randomUUID().toString());
        appointment.setType(AppointmentType.Fooball);


        return appointment;
    }
}
