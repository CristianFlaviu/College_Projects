package ro.utcn.sd.mapper;

import ro.utcn.sd.dto.AppointmentDto;
import ro.utcn.sd.entity.Appointment;
import ro.utcn.sd.entity.Room;

public class AppointmentMapper {


    public static Appointment DtoToAppointment(AppointmentDto appointmentDto, Room room)
    {

        Appointment appointment=new Appointment();

        appointment.setId(appointmentDto.getId());
        appointment.setDay(appointmentDto.getDay());
        appointment.setStartHour(appointmentDto.getStartHour());
        appointment.setRoom(room);

        return appointment;
    }

    public static AppointmentDto AppointmentToDto(Appointment appointment)
    {
        AppointmentDto appointmentDto=new AppointmentDto();

        appointmentDto.setId(appointment.getId());
        appointmentDto.setType(appointment.getType());
        appointmentDto.setDay(appointment.getDay());
        appointmentDto.setStartHour(appointment.getStartHour());
        appointmentDto.setRoomNumber(appointment.getRoom().getRoomNumber());

        return appointmentDto;
    }
}

