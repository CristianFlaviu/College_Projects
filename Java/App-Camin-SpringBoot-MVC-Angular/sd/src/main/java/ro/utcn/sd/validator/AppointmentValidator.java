package ro.utcn.sd.validator;

import ro.utcn.sd.Exceptions.DuplicateDataException;
import ro.utcn.sd.Exceptions.MoreThanTwoAppoinmentsException;
import ro.utcn.sd.controller.UserController;
import ro.utcn.sd.entity.Appointment;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AppointmentValidator {

    private final static Logger LOGGER = Logger.getLogger(AppointmentValidator.class.getName());

    public static Boolean validate(Appointment appointment, List<Appointment> listApp)
    {
        if(Objects.isNull(appointment))
            return false;

        String day=appointment.getDay();
        String startHour=appointment.getStartHour();
        String type=appointment.getType();
        String roomNumber=appointment.getRoom().getRoomNumber();

        if(listApp.stream().filter(a->a.getDay().equals(day) && a.getStartHour().equals(startHour)&&a.getType().equals(type)).collect(Collectors.toList()).size()>0)
        {
            throw  new DuplicateDataException("data already inserted");
        }
        if(listApp.stream().filter(a->a.getRoom().getRoomNumber().equals(roomNumber)&& a.getType().equals(type)).collect(Collectors.toList()).size()>=2)
        {
            throw new MoreThanTwoAppoinmentsException("");
        }
        try {
            Integer.valueOf(startHour);

        }catch (Exception e)
        {
            return false;
        }


        return true;



    }
}
