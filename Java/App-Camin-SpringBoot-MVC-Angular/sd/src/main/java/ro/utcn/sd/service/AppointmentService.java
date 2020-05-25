package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Appointment;
import ro.utcn.sd.repository.AppointmentRepo;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public void add(Appointment appointment)
    {
        appointmentRepo.save(appointment);
    }

    public Appointment getById(String id)
    {
       return appointmentRepo.findById(id).orElse(null);
    }

    public List<Appointment> getAllByType(String type)
    {
        return appointmentRepo.findAllByType(type);
    }

    public List<Appointment> getAll()
    {
        return appointmentRepo.findAll();
    }

    public void deleteById (String id)
    {
        appointmentRepo.deleteById(id);
    }
}
