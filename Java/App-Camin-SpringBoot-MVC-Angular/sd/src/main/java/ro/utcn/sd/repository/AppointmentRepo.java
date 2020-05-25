package ro.utcn.sd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,String> {

    public List<Appointment> findAllByType(String type);
}
